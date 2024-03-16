package com.comitr.github.network

import android.content.Context
import android.os.Looper
import android.widget.Toast

fun makeToast(context:Context,success:Boolean,msg1:String,msg2:String){
    if(success){
        if(Looper.myLooper()==null){
            Looper.prepare()
            Toast.makeText(context,"$msg1", Toast.LENGTH_SHORT).show()
        }
        else{
            Looper.loop()
            Toast.makeText(context,"$msg1", Toast.LENGTH_SHORT).show()
        }
    }
    else{
        if(Looper.myLooper()==null){
            Looper.prepare()
            Toast.makeText(context,"$msg2", Toast.LENGTH_SHORT).show()
        }
        else{
            Looper.loop()
            Toast.makeText(context,"$msg2", Toast.LENGTH_SHORT).show()
        }
    }
}