package io.lvivscalaclub.simplejson

import java.time.LocalDate

import io.lvivscalaclub.simplejson.models._

object Main extends App {

  implicit val addressToJson: JsonWriter[Address] = new JsonWriter[Address] {
    def write(obj: Address): String =
      s"""
        {
          "id": ${obj.id},
          "street": "${obj.street}"
        }
      """
  }

  implicit val personToJson: JsonWriter[Person] =
    (obj: Person) =>
      s"""
        {
          "id": ${obj.id},
          "birthDate": "${obj.birthDate.toString}",
          "address": ${obj.address.toJson}
        }
      """

  implicit def seqToJson[A](implicit writer: JsonWriter[A]): JsonWriter[Traversable[A]] =
    (obj: Traversable[A]) => obj.map(_.toJson).mkString("[", ", ", "]")

  val address1 = Address(15, "Lemkivska")
  val address2 = Address(1, "Bunina")
  val person1 = Person(1, LocalDate.now(), address1)
  val person2 = Person(2, LocalDate.now(), address2)

  println(Seq(person1, person2).toJson)
  println(List(address1, address2).toJson)
}
