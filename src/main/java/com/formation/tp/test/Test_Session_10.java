package com.formation.tp.test;

import com.formation.tp.app.Master_Context;
import com.formation.tp.date.Date_Ticket;

public class Test_Session_10 {

    public void test(){

        // -- demo
        Date_Ticket ref_ColorTicket = Master_Context.ref_ColorContext.get_ColorTicket();

        while(ref_ColorTicket.getValue(Date_Ticket.ref_String_Key_Status).equals(Date_Ticket.ref_String_Value_Status_Create)
                || ref_ColorTicket.getValue(Date_Ticket.ref_String_Key_Status).equals(Date_Ticket.ref_String_Value_Status_Treated)) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("====== ATTENTE =========");
            System.out.println("TICKET STATUS=" + ref_ColorTicket.getValue(Date_Ticket.ref_String_Key_Status));
            System.out.println("TICKET REFUSED REASON=" + ref_ColorTicket.getValue(Date_Ticket.ref_String_Key_Refused_Reason));
            System.out.println("TICKET COLOR=" + ref_ColorTicket.getValue(Date_Ticket.ref_String_Key_Color));

        }


        System.out.println("====== FIN DE L ATTENTE =========");
        System.out.println("TICKET STATUS=" + ref_ColorTicket.getValue(Date_Ticket.ref_String_Key_Status));
        System.out.println("TICKET REFUSED REASON=" + ref_ColorTicket.getValue(Date_Ticket.ref_String_Key_Refused_Reason));
        System.out.println("TICKET COLOR=" + ref_ColorTicket.getValue(Date_Ticket.ref_String_Key_Color));

    }
}
