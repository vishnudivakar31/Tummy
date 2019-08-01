package com.wanderingThinker.Tummy.supportingdocuments;

public class TummyDatatypes {
    public enum Roles {
        ADMIN, USER, GROUP_LEADER
    }

    public enum RequestType {
        CIRCLE_ADD,
        CIRCLE_REMOVE,
        CIRCLE_BLOCK,
        CIRCLE_UNBLOCK,
        RECIPE_ABSUVIE,
        RECIPE_CLEAR_ABUSE
    }

    public enum RequestStatus {
        NEW,
        UPDATED,
        CLOSED
    }
}
