package org.example.ProvedoresDeDatos;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class ProvedorDeDatosHIkari {

    private static volatile HikariDataSource ds;

    ProvedorDeDatosHIkari() {}

    public static DataSource get() {
        if (ds == null) {
            synchronized (ProvedorDeDatosHIkari.class) {
                if (ds == null) {
                    HikariConfig cfg = new HikariConfig();


                    cfg.setJdbcUrl(env("PG_URL", "jdbc:postgresql://localhost:5432/Aplicacion46"));
                    cfg.setUsername(env("PG_USER", "postgres"));
                    cfg.setPassword(env("PG_PASSWORD", "pwd"));

                    cfg.setMaximumPoolSize(intEnv("PG_POOL_SIZE", 10));
                    cfg.setMinimumIdle(intEnv("PG_MIN_IDLE", 2));
                    cfg.setConnectionTimeout(longEnv("PG_CONN_TIMEOUT_MS", 10_000));
                    cfg.setIdleTimeout(longEnv("PG_IDLE_TIMEOUT_MS", 60_000));
                    cfg.setMaxLifetime(longEnv("PG_MAX_LIFETIME_MS", 30 * 60_000));
                    cfg.setPoolName(env("PG_POOL_NAME", "pg-pool"));

                    ds = new HikariDataSource(cfg);

                    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                        HikariDataSource x = ds;
                        if (x != null && !x.isClosed()) x.close();
                    }));
                }
            }
        }
        return ds;
    }

    private static String env(String k, String def) {
        String v = System.getenv(k);
        return (v == null || v.isEmpty()) ? def : v;
    }

    private static int intEnv(String k, int def) {
        try { return Integer.parseInt(env(k, String.valueOf(def))); }
        catch (Exception e) { return def; }
    }

    private static long longEnv(String k, long def) {
        try { return Long.parseLong(env(k, String.valueOf(def))); }
        catch (Exception e) { return def; }
    }


}
