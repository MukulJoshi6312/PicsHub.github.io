package com.mukuljoshi.PicsHub;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleten {

   private RequestQueue requestQueue;
   private static VolleySingleten mInstance;

   private VolleySingleten(Context context){
       requestQueue = Volley.newRequestQueue(context.getApplicationContext());
   }

   public static synchronized  VolleySingleten getInstance(Context context){

       if (mInstance == null){
           mInstance = new VolleySingleten(context);
       }
       return mInstance;
   }
   public RequestQueue getRequestQueue(){
       return requestQueue;
   }


}
