package com.formation.tp.color;

import java.util.HashMap;
import java.util.UUID;

public class Color_Ticket {

    // -- CONS
    final public String ref_String_Value_Default = UUID.randomUUID().toString();

    final public static String ref_String_Key_Status = "ref_String_Key_Status";
        final public String ref_String_Value_Status_Create = "ref_String_Value_Status_Create";
        final public String ref_String_Value_Status_Complete = "ref_String_Value_Status_Complete";
        final public String ref_String_Value_Status_Refused = "ref_String_Value_Status_Refused";


    final public static String ref_String_Key_Refused_Reason = "ref_String_Key_Refused_Reason";
    final public String ref_String_Value_Refused_Reason = "ref_String_Value_Refused_Reason";

    final public static String ref_String_Key_Color = "ref_String_Key_Color";

    // -- REF
    final private Object ref_Object_Lock_HolderValue = new Object();
    final private HashMap<String, String> ref_HashMap_HolderValue = new HashMap<>();



    // -- CONSTRUCTOR ------------------------------------------------------------------------------

    public Color_Ticket () {

        this.init();

    }



    // -- INITIALISATOR ----------------------------------------------------------------------------

    private void init () {

        ref_HashMap_HolderValue.put(ref_String_Key_Status, ref_String_Value_Status_Create);
        ref_HashMap_HolderValue.put(ref_String_Key_Refused_Reason, ref_String_Value_Default);
        ref_HashMap_HolderValue.put(ref_String_Key_Color, ref_String_Value_Default);

    }



    // -- OUTER CALLBACK -----------------------------------------------------------------------------

    public String getValue (String ref_String_getValue_Key) {

        // -- Init
        String ref_String_Value = null;

        // -- Sync
        synchronized (this.ref_Object_Lock_HolderValue){

            // -- Work
            ref_String_Value = ref_HashMap_HolderValue.get(ref_String_getValue_Key);

        }

        // -- Commit
        return ref_String_Value;

    }



    // -- INNER CALLBACK -----------------------------------------------------------------------------

    public void setValue (String ref_String_setValue_Key, String ref_String_setValue_Value) {

        // -- Sync
        synchronized (this.ref_Object_Lock_HolderValue){

            // -- Work
            ref_HashMap_HolderValue.put(ref_String_setValue_Key, ref_String_setValue_Value);

        }

    }



}
