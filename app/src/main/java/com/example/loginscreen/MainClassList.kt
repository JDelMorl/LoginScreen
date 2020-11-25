package com.example.loginscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainClassList : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_class_list)

        val swDegreeCert = findViewById<Switch>(R.id.switchDegree)
        val spnDegree = findViewById<Spinner>(R.id.idSpinnerMajor)
        val spnCertificate = findViewById<Spinner>(R.id.idSpinnerCertificate)
        val txtCertificate = findViewById<TextView>(R.id.txtCert)
        val txtDegree = findViewById<TextView>(R.id.textView4)
        val btnNext = findViewById<Button>(R.id.idButton)

        val firstName = findViewById<EditText>(R.id.idFName)
        val lastName = findViewById<EditText>(R.id.idLName)
        val phone = findViewById<EditText>(R.id.idPhoneEntry)

        val spMonth = findViewById<Spinner>(R.id.idMonths)
        val txtDay = findViewById<EditText>(R.id.idDay)
        val txtYear = findViewById<EditText>(R.id.idYear)

        firstName.requestFocus()

        swDegreeCert.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                spnDegree.visibility = View.VISIBLE
                txtDegree.visibility = View.VISIBLE
                spnCertificate.visibility = View.GONE
                txtCertificate.visibility = View.GONE
            } else {
                spnDegree.visibility = View.GONE
                txtDegree.visibility = View.GONE
                spnCertificate.visibility = View.VISIBLE
                txtCertificate.visibility = View.VISIBLE
            }
        }

        btnNext.setOnClickListener {
            if (checkData()) {
                var doBirth = ""
                doBirth =
                    spMonth.selectedItem.toString() + "/" + txtDay.text.toString() + "/" + txtYear.text.toString()

                val nextScreen = Intent(this@MainClassList, ChooseClass::class.java)
                nextScreen.putExtra("FirstName", firstName.text.toString())
                nextScreen.putExtra("LastName", lastName.text.toString())
                nextScreen.putExtra("Phone", phone.text.toString())
                nextScreen.putExtra("BirthDate", doBirth)

                if (spnDegree.visibility == View.VISIBLE) {
                    nextScreen.putExtra("isDegreeCert", "Degree")
                    nextScreen.putExtra("degreeCert", spnDegree.selectedItem.toString())
                } else {
                    nextScreen.putExtra("isDegreeCert", "Certificate")
                    nextScreen.putExtra("degreeCert", spnCertificate.selectedItem.toString())
                }

                startActivity(nextScreen)
            }
        }

    }
        private fun checkData(): Boolean{
            val firstName = findViewById<EditText>(R.id.idFName)
            val lastName = findViewById<EditText>(R.id.idLName)
            val phone = findViewById<EditText>(R.id.idPhoneEntry)
            val txtDay = findViewById<EditText>(R.id.idDay)
            val txtYear = findViewById<EditText>(R.id.idYear)

            if (firstName.text.toString().isEmpty()){
                firstName.error = "Invalid First Name"
                firstName.requestFocus()
                return false
            }

            if (lastName.text.toString().isEmpty()){
                lastName.error = "Invalid Last Name"
                lastName.requestFocus()
                return false
            }

            if (phone.text.toString().isEmpty()){
                phone.error = "Invalid Phone Number"
                phone.requestFocus()
                return false
            }

            if (txtDay.text.toString().isEmpty()){
                txtDay.error = "Invalid Data Selection(Day)"
                txtDay.requestFocus()
                return false
            }

            if (txtYear.text.toString().isEmpty()){
                txtYear.error = "Invalid Data Selection(Year)"
                txtYear.requestFocus()
                return false
            }


            return true
        }


}