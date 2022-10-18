package it.stepwise.alfresco.restapiclient;

import org.json.JSONObject;

import java.lang.reflect.Field;

public abstract class InputBody {

    public JSONObject toJSON() throws IllegalAccessException {
        Field[] fields = this.getClass().getDeclaredFields();
        JSONObject jsonObject = new JSONObject();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(this) != null) {
                jsonObject.put(field.getName(), field.get(this));
            }
        }
        return jsonObject;
    }

}