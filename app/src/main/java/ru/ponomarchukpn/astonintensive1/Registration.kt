package ru.ponomarchukpn.astonintensive1

sealed class Registration {

    class EmployerReg(private val employer: User) : Registration() {

        fun employerMessage(): String {
            var message = with(employer) {
                String.format("%s: %s %s", EMPLOYER_REG, lastName, firstName)
            }

            employer.patronymic?.let {
                message = String.format("%s %s", message, it)
            }

            return message
        }
    }

    class EmployeeReg(private val employee: User) : Registration() {

        fun employeeMessage(): String {
            var message = with(employee) {
                String.format("%s: %s %s", EMPLOYEE_REG, lastName, firstName)
            }

            employee.patronymic?.let {
                message = String.format("%s %s", message, it)
            }

            return message
        }
    }

    companion object {

        private const val EMPLOYEE_REG = "Регистрация работника"
        private const val EMPLOYER_REG = "Регистрация работодателя"
    }
}
