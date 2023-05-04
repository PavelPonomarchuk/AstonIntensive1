package ru.ponomarchukpn.astonintensive1

sealed class Registration {

    class EmployerReg(val employer: User) : Registration() {

        fun registerAsEmployer(): String {
            //TODO

            return ""
        }
    }

    class EmployeeReg(val employee: User) : Registration() {

        fun registerAsEmployee(): String {
            //TODO

            return ""
        }
    }
}
