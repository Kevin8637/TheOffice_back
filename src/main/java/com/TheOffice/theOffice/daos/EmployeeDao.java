package com.TheOffice.theOffice.daos;

import com.TheOffice.theOffice.entities.Employee.Employee;
import com.TheOffice.theOffice.entities.Employee.Mood;
import com.TheOffice.theOffice.entities.Employee.Sex;
import com.TheOffice.theOffice.entities.Employee.Status;
import com.TheOffice.theOffice.entities.Employee.Job;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Employee> employeeRowMapper = (rs, _) -> new Employee(
            rs.getLong("id"),
            rs.getString("name"),
            Sex.valueOf(rs.getString("sex")),
            rs.getInt("seniority"),
            rs.getBigDecimal("salary"),
            rs.getInt("level"),
            Mood.valueOf(rs.getString("mood")),
            Status.valueOf(rs.getString("status")),
            Job.valueOf(rs.getString("job")),
            rs.getInt("health")
    );

    public List<Employee> findAll(){
        String sql = "SELECT * FROM Employee";
        return jdbcTemplate.query(sql, employeeRowMapper);
    }

    public Employee findById(Long id){
        String sql = "SELECT * FROM Employee WHERE id = ?";
        return jdbcTemplate.query(sql, employeeRowMapper)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Salarié non trouvé"));
    }
}
