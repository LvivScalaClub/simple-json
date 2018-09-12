package io.lvivscalaclub.simplejson

import java.time.LocalDate

object Main extends App {

  case class Person(id: Int, birthDate: LocalDate, address: Address)

  case class Address(id: Int, street: String)

  implicit val addressToJson: JsonWriter[Address] = new JsonWriter[Address] {
    def write(obj: Address): String =
      s"""
           {
             "id": ${obj.id},
             "street": "${obj.street}"
           }
         """.stripMargin
  }

  implicit val personToJson: JsonWriter[Person] =
    (obj: Person) =>
      s"""
           {
             "id": ${obj.id},
             "birthDate": "${obj.birthDate.toString}",
             "address": ${obj.address.toJson}
           }
          """.stripMargin

  implicit def listToJson[A](implicit writer: JsonWriter[A]): JsonWriter[List[A]] = new JsonWriter[List[A]] {
    def write(obj: List[A]): String =
      s"""
            [
              ${obj.map(o => o.toJson).mkString(", ")}
            ]
          """.stripMargin
  }

  val p1 = Person(1, LocalDate.now(), Address(1, "Lemkivska"))
  val a1 = Address(1, "Lemkivska")

  println(List(p1, p1).toJson)
  println(List(a1, a1).toJson)
}
