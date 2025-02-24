package com.TheOffice.theOffice.daos;

import com.TheOffice.theOffice.entities.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class CompanyDao {

    private final JdbcTemplate jdbcTemplate;

    public CompanyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Company> companyRowMapper = (rs, _) -> new Company(
            rs.getLong("id"),
            rs.getString("sector"),
            rs.getString("name"),
            rs.getDate("creation_date"),
            rs.getLong("id_company")
    );

    public Company findById(Long id) {
        String sql = "SELECT * FROM Company WHERE id = ?";
        return jdbcTemplate.query(sql, companyRowMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));
    }

    public List<Company> findAll(){
        String sql = "SELECT * FROM Company";
        return jdbcTemplate.query(sql, companyRowMapper);
    }

    public int save(String email, Date invoice_date, double total_price) {
        String sql = "INSERT INTO invoice (email, invoice_date, total_price) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.setDate(2, new java.sql.Date(invoice_date.getTime()));
            ps.setDouble(3, total_price);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public Company update(Long id, Company company) {
        if (!companyExists(id)) {
            throw new RuntimeException("Facture avec l'ID : " + id + " n'existe pas");
        }

        String sql = "UPDATE Company SET sector = ?, name = ?, creation_date = ? WHERE id_user = ?";
        int rowsAffected = jdbcTemplate.update(sql, company.getSector(), company.getName(), company.getCreation_date(), company.getId_user(), id);

        if (rowsAffected <= 0) {
            throw new RuntimeException("Échec de la mise à jour de l'entreprise avec l'ID : " + id);
        }
        return company;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM Company WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public boolean companyExists(Long id) {
        String sql = "SELECT COUNT(*) FROM Company WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id) > 0;
    }
}
