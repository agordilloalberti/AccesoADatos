package CRUD.Model

data class Empleado(
    val id:String,
    val apellido:String,
    val dpto:String,
    val salario: String
){
    override fun toString(): String {
        return "Empleado(id='$id', apellido='$apellido', dpto='$dpto', salario=$salario)"
    }
}
