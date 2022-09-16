package br.senai.sp.jandira.imc20.utils

import android.content.Context
import br.senai.sp.jandira.imc20.R
import java.security.AccessControlContext
import kotlin.math.pow

fun getBmi(wheight: Int, height: Double): Double {
    return wheight/height.pow(2);
}

fun getStatusBmi(bmi:Double, context: Context): String{

    if(bmi <= 18.5){
        return context.getString(R.string.underweight)
    } else if( bmi > 18.5 && bmi <= 25){
        return context.getString(R.string.ideal_weight)
    }else if( bmi > 25 && bmi <= 30){
        return context.getString(R.string.overweight)
    }else if( bmi > 30 && bmi <= 35){
        return context.getString(R.string.obesity1)
    }else if( bmi > 35 && bmi <= 40){
        return context.getString(R.string.obesity2)
    }else{
        return context.getString(R.string.obesity3)
    }
}