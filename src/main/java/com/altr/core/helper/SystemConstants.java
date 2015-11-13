package com.altr.core.helper;

public interface SystemConstants {

    public static class IDS {
        public static final String DEFAULT_OBJECT = "10";
    }

    public static class SQL {
        public static final String GET_GROUPS_BY_OBJECT_ID = "select distinct ag.subgroup, ag.show_order, ag.attr_group_id, ag.flags, ag.name\n" +
                "from\n" +
                "t_objects o,\n" +
                "t_attr_object_types aot,\n" +
                "t_attributes a,\n" +
                "t_attr_groups ag\n" +
                "where o.object_id = ?\n" +
                "and aot.object_type_id = o.object_type_id\n" +
                "and a.attr_id = aot.attr_id\n" +
                "and ag.attr_group_id = a.attr_group_id\n" +
                "order by ag.show_order DESC";
        public static final String GET_CHILDREN_ID_BY_PARENT = "select object_id from t_objects where parent_id = ?";
        public static final String GET_PARAMETER_VALUE_BY_OBJECT_AND_ATTR = "select value from t_params where object_id = ? and attr_id = ?";
        public static final String GET_PARAMETER_LIST_VALUE_BY_OBJECT_AND_ATTR = "select list_value_id from t_params where object_id = ? and attr_id = ?";
        public static final String GET_STRUCTURAL_OBJECTS_BY_GROUP = "select o.object_id\n" +
                "from t_attributes a,\n" +
                "t_attr_type_defs atd,\n" +
                "t_object_types ot,\n" +
                "t_objects o\n" +
                "where a.attr_id in (select attr_id from t_attributes where attr_group_id = ?)\n" +
                "and a.attr_type_id = 3\n" +
                "and atd.attr_type_def_id = a.attr_type_def_id\n" +
                "and ot.object_type_id = atd.object_type_id\n" +
                "and o.object_type_id = ot.object_type_id";
        public static final String GET_ATTRIBUTES_BY_GROUP_AND_TYPE = "select a.attr_id\n" +
                "from t_attributes a,\n" +
                "t_attr_object_types aot\n" +
                "where a.attr_group_id = ?\n" +
                "and a.attr_type_id in (1, 6, 7)\n" +
                "and aot.attr_id = a.attr_id\n" +
                "and aot.object_type_id = ?";
        public static final String GET_ATTRIBUTES_BY_GROUP_AND_OBJECT_TYPE_AND_ATTR_TYPE = "select a.attr_id\n" +
                "from t_attributes a,\n" +
                "t_attr_object_types aot\n" +
                "where a.attr_group_id = ?\n" +
                "and a.attr_type_id = ?\n" +
                "and aot.attr_id = a.attr_id\n" +
                "and aot.object_type_id = ?";
        public static final String GET_START_GROUP_BY_OBJECT_ID = "select distinct ag.attr_group_id\n" +
                "from\n" +
                "t_objects o,\n" +
                "t_attr_object_types aot,\n" +
                "t_attributes a,\n" +
                "t_attr_groups ag\n" +
                "where o.object_id = ?\n" +
                "and aot.object_type_id = o.object_type_id\n" +
                "and a.attr_id = aot.attr_id\n" +
                "and ag.attr_group_id = a.attr_group_id\n" +
                "order by ag.show_order";

        public static final String GET_CHILDREN_OBJECTS_BY_GROUP_AND_OBJECT = "select o.object_id\n" +
                "from t_attributes a,\n" +
                "t_attr_type_defs atd,\n" +
                "t_object_types ot,\n" +
                "t_objects o\n" +
                "where a.attr_id in (select attr_id from t_attributes where attr_group_id = ?)\n" +
                "and a.attr_type_id = 4\n" +
                "and atd.attr_type_def_id = a.attr_type_def_id\n" +
                "and ot.object_type_id = atd.object_type_id\n" +
                "and o.object_type_id = ot.object_type_id\n" +
                "and o.parent_id = ?";
    }
}
