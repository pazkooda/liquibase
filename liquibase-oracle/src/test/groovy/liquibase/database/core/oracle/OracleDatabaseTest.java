package liquibase.database.core.oracle;

import liquibase.database.AbstractJdbcDatabaseTest;
import liquibase.database.Database;
import liquibase.structure.ObjectName;
import liquibase.structure.core.Table;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests for {@link liquibase.database.core.oracle.OracleDatabase}.
 */
public class OracleDatabaseTest extends AbstractJdbcDatabaseTest {

    public OracleDatabaseTest() throws Exception {
        super(new OracleDatabase());
    }

    @Override
    protected String getProductNameString() {
        return "Oracle";
    }

     @Override
     @Test
    public void escapeTableName_noSchema() {
        Database database = getDatabase();
        assertEquals("tableName", database.escapeObjectName("tableName", Table.class));
    }

    @Test
    public void escapeTableName_withSchema() {
        Database database = getDatabase();
        assertEquals("catalogName.tableName", database.escapeObjectName(new ObjectName("catalogName", "schemaName", "tableName"), Table.class));
    }

    @Override
    @Test
    public void supportsInitiallyDeferrableColumns() {
        assertTrue(getDatabase().supportsInitiallyDeferrableColumns());
    }


    @Override
    @Test
    public void getCurrentDateTimeFunction() {
        Assert.assertEquals("SYSTIMESTAMP", getDatabase().getCurrentDateTimeFunction());
    }

    public void testGetDefaultDriver() {
        Database database = new OracleDatabase();

        assertEquals("oracle.jdbc.OracleDriver", database.getDefaultDriver("jdbc:oracle:thin:@localhost/XE"));

        assertNull(database.getDefaultDriver("jdbc:db2://localhost;databaseName=liquibase"));
    }

}

