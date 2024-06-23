import java.util.*;

public class Main {

    static class DataStore {
        private Map<String, Table> tables;

        public Table createTable(String tableName, List<Column> columns) {
            if (tables.containsKey(tableName)) {
                throw new IllegalArgumentException("table with same name already exists");
            }
            Table table = new Table(tableName, columns);
            tables.put(tableName, table);
            return table;
        }


        public Map<String, Table> getTables() {
            return tables;
        }

        public void setTables(Map<String, Table> tables) {
            this.tables = tables;
        }
    }

    static class Table {
        private String name;
        private List<Column> columns;
        private List<Record> records;

        public Table(String name, List<Column> columns) {
            this.name = name;
            this.columns = columns;
            this.records = new ArrayList<>();
        }

        public void insertRow(Record record) {
            if (record.getValues().size() != columns.size()) {
                throw new IllegalArgumentException("wrong number of columns");
            }
            validateRecord(record);
            int pIndex = 0;
            for (Column column : this.columns) {
                if (column.isPrimaryKey()) {
                    break;
                }
                pIndex++;
            }
            for (Record oldRecord : this.records) {
                if (oldRecord.getValues().get(pIndex).equals(record.getValues())) {
                    throw new IllegalArgumentException("row with same PK already exists");
                }
            }
            records.add(record);
        }

        public void updateRow(Map<Integer, Object> newValues, String conditionColumn, String expectedValue) {
            int cIndex = 0;
            for (Column column : this.columns) {
                if (column.getName().equals(conditionColumn)) {
                    break;
                }
                cIndex++;
            }
            // validate new values
            for (Record record : this.records) {
                if (record.getValues().get(cIndex).equals(expectedValue)) {
                    for (Integer index : newValues.keySet()) {
                        record.getValues().set(index, newValues.get(index));
                    }
                }
            }
        }

        public void deleteRow(Record record) {
            records.remove(record);
        }

        public List<Record> search (String conditionColumn, String expectedValue) {
            return new ArrayList<>();
        }

        private void validateRecord(Record record) {
            for (int i = 0; i < record.getValues().size(); i++) {
                Column column = columns.get(i);
                if (column.getConstraints() != null) {
                    for (Constraint constraint : column.getConstraints()) {
                        constraint.validate();
                    }
                }
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Column> getColumns() {
            return columns;
        }

        public void setColumns(List<Column> columns) {
            this.columns = columns;
        }

        public List<Record> getRecords() {
            return records;
        }

        public void setRecords(List<Record> records) {
            this.records = records;
        }
    }

    static class Column {
        private String name;
        private List<Constraint> constraints;
        private Datatype datatype;
        boolean isPrimaryKey;

        public Column(String name, List<Constraint> constraints, Datatype datatype, boolean isPrimaryKey) {
            this.name = name;
            this.constraints = constraints;
            this.datatype = datatype;
            this.isPrimaryKey = isPrimaryKey;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Constraint> getConstraints() {
            return constraints;
        }

        public void setConstraints(List<Constraint> constraints) {
            this.constraints = constraints;
        }

        public Datatype getDatatype() {
            return datatype;
        }

        public void setDatatype(Datatype datatype) {
            this.datatype = datatype;
        }

        public boolean isPrimaryKey() {
            return isPrimaryKey;
        }

        public void setPrimaryKey(boolean primaryKey) {
            isPrimaryKey = primaryKey;
        }
    }

    static enum Datatype {
        INT, STRING
    }

    static class Record {
        private List<Object> values;

        public List<Object> getValues() {
            return values;
        }

        public void setValues(List<Object> values) {
            this.values = values;
        }
    }

    interface Constraint {
        boolean validate();
    }

    static class LengthConstraint implements Constraint {

        int maxLength;

        public LengthConstraint(int maxLength) {
            this.maxLength = maxLength;
        }

        public boolean validate() {
            return true;
        }
    }

    static class MaxValueConstraint implements Constraint {
        public boolean validate() {
            return true;
        }
    }


    public static void main(String[] args) {
        DataStore ds = new DataStore();
        Constraint constraint1 = new LengthConstraint(10);
        Column col1 = new Column("col1", Collections.singletonList(constraint1), Datatype.STRING, true);
        Column col2 = new Column("col1", Collections.singletonList(constraint1), Datatype.STRING, false);
//        ds.createTable("table1", )
    }
}