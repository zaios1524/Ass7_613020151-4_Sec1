package com.myweb.lab7dialogrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog_layout.*
import kotlinx.android.synthetic.main.add_dialog_layout.view.*

class MainActivity : AppCompatActivity() {
    val employeeList= arrayListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employeeData()
        recycler_view.adapter=EmployeeAdapter(this.employeeList,applicationContext)
        recycler_view.layoutManager=LinearLayoutManager(applicationContext)

    }
    fun employeeData(){
        employeeList.add(Employee("jirakit pawa","Male","jirakit.pawa@kkumail.com",90000))
        employeeList.add(Employee("EMINEM","Female","EMINEM.GOD@kkumail.com",500))
    }

    fun addEmployee(view:View){
        val mDialogView=LayoutInflater.from(this).inflate(R.layout.add_dialog_layout,null)
        val myBuilder=AlertDialog.Builder(this)
        myBuilder.setView(mDialogView)
        val mAlertDialog = myBuilder.show()
        mAlertDialog.btn_add.setOnClickListener() {
            var gender = ""
            if (mAlertDialog.btn_male.isChecked){
                gender="Male"
            }else if (mAlertDialog.btn_female.isChecked){
                gender="Female"
            }
            employeeList.add(
                Employee(
                    mAlertDialog.edt_name.text.toString(),
                    gender,
                    mAlertDialog.edt_email.text.toString(),
                    mAlertDialog.edt_salary.text.toString().toInt()
                )
            )
            recycler_view.adapter?.notifyDataSetChanged()
            Toast.makeText(
                applicationContext,
                "The Employee is added successfully",
                Toast.LENGTH_LONG
            ).show()
            mAlertDialog.dismiss()
        }
        mAlertDialog.btn_cancel.setOnClickListener() {
            mAlertDialog.dismiss()
        }
    }

}