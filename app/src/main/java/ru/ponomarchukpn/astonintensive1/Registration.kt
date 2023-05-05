package ru.ponomarchukpn.astonintensive1

sealed class Registration {

    class EmployerReg(private val employer: User) : Registration() {

        fun employerMessage(): String = with(employer) {
            val initials = listOfNotNull(lastName, firstName, patronymic)
            "$EMPLOYER_REG : ${initials.joinToString(" ")}"
        }
    }

    class EmployeeReg(private val employee: User) : Registration() {

        fun employeeMessage(): String = with(employee) {
            val initials = listOfNotNull(lastName, firstName, patronymic)
            "$EMPLOYEE_REG : ${initials.joinToString(" ")}"
        }
    }

    companion object {

        private const val EMPLOYEE_REG = "Регистрация работника"
        private const val EMPLOYER_REG = "Регистрация работодателя"
    }
}
