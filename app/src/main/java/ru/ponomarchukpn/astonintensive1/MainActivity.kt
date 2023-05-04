package ru.ponomarchukpn.astonintensive1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.ponomarchukpn.astonintensive1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO логика работы с интерфейсом

        binding.btnEmployee.setOnClickListener {
            //TODO
        }

        binding.btnEmployer.setOnClickListener {
            //TODO
        }
    }

    private fun processRegistration(reg: Registration) {
        RegistrationMessage().apply {
            message = registrationMessage(reg)
        }.also {
            showToast(it)
        }
    }

    private fun registrationMessage(reg: Registration): String = reg.run {
        when (reg) {
            is Registration.EmployeeReg -> reg.employeeMessage()
            is Registration.EmployerReg -> reg.employerMessage()
        }
    }

    private fun showToast(regMessage: RegistrationMessage) {
        regMessage.message?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}
