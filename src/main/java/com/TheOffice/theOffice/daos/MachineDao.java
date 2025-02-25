package com.TheOffice.theOffice.daos;

import com.TheOffice.theOffice.entities.Local.Local;
import com.TheOffice.theOffice.entities.Machine.Machine;
import com.TheOffice.theOffice.entities.Machine.ProductionQuality;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

//GET, POST, PULL
@Repository
public class MachineDao {

    private final JdbcTemplate jdbcTemplate;

    public MachineDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Machine> machineRowMapper = (rs, _) -> new Machine (
            rs.getLong("id"),
            rs.getString("name"),
            ProductionQuality.valueOf(rs.getString("productionQuality")),
            rs.getBigDecimal("price"),
            rs.getBigDecimal("maintenanceCost")
    );

    public List<Machine> findAll(){
        String sql = "SELECT * FROM Machine";
        return jdbcTemplate.query(sql, machineRowMapper);
    }

    public Machine findById(Long id){
        String sql = "SELECT * FROM Machine WHERE id = ?";
        return jdbcTemplate.query(sql, machineRowMapper)
                .stream()
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Machine non trouvée"));
    }

    public int save(String name, String productionQuality, BigDecimal price, BigDecimal maintenanceCost) {
        String sql = "INSERT INTO Local (name, productionQuality, price, maintenanceCost) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, productionQuality);
            ps.setBigDecimal(3, price);
            ps.setBigDecimal(4, maintenanceCost);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public Machine update(Long id, Machine machine) {
        if (!machineExists(id)) {
            throw new RuntimeException("Machine avec l'ID : " + id + " n'existe pas");
        }

        String sql = "UPDATE Machine SET name = ?, productionQuality = ?, price = ?, maintenanceCost = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, machine.getName(), machine.getProductionQuality(), machine.getPrice(), machine.getMaintenanceCost(), id);

        if (rowsAffected <= 0) {
            throw new RuntimeException("Échec de la mise à jour de la machine avec l'ID : " + id);
        }
        return machine;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM Machine WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public boolean machineExists(Long id) {
        String sql = "SELECT COUNT(*) FROM Machine WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id) > 0;
    }
}
