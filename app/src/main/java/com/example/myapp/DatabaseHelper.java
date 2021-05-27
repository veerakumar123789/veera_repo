//package com.example.myapp;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//
//import okhttp3.internal.concurrent.Task;
//
//public class DatabaseHelper {
//private SharedPreferences sp;
//private Gson gson;
// public  DatabaseHelper(Context context){
//
//     sp =context.getSharedPreferences("taskDatabase",context.MODE_PRIVATE);
//     gson=new Gson();
// }
//
//    public void saveTask(ArrayList<Task> tasks){
//     SharedPreferences.Editor editor =sp.edit();
//     editor.putString("tasks", String.valueOf(tasks));
//     editor.apply();
//    }
//
//    public Object getTask(){
//     String taskString =sp.getString("task",null);
//     Type taskList =new TypeToken<ArrayList<Task>>(){}.getType();
//    ArrayList<Task> tasks = gson.fromJson(taskString,taskList);
//
//
//    if(tasks != null) return tasks;
//    else return new ArrayList<>();
//
//    }
//}
//
