package com.bookvault.bookvault.configuration.migration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;


import javax.sql.DataSource;

@Profile({"prod"})
@Configuration
public class DatabaseMigrationConfig {
    private static final String resourcePath = "classpath:/db/migration";
    private static final String sampleDataPath = "classpath:/db/migration";

    private static final String dataSourceUrl = "jdbc:postgresql://localhost:5432/bookvaultdb";
    private static final String username = "postgres";
    private static final String password = "postgres";

    @Bean("flyway")
    public Flyway flyway() {

        FluentConfiguration fluentConfiguration = Flyway.configure().dataSource(dataSourceUrl, username, password);

        fluentConfiguration.locations(resourcePath, sampleDataPath);

        Flyway flyway = fluentConfiguration.load();

        flyway.clean();
        flyway.migrate();

        return flyway;
    }

    @DependsOn("flyway")
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(dataSourceUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
