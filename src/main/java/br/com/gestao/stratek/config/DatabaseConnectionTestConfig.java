package br.com.gestao.stratek.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseConnectionTestConfig {

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void testConnection() {
        try {
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);

            if (result != null && result == 1) {
                log.info("✅ Conexão com o banco de dados estabelecida com sucesso!");
            }
        } catch (Exception e) {
            log.error("❌ Erro ao conectar com o banco de dados", e);
        }
    }
}