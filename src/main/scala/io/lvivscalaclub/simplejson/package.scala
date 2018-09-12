package io.lvivscalaclub

package object simplejson {
  implicit class JsonOps[A](obj: A) {
    def toJson(implicit jsonWriter: JsonWriter[A]): String = {
      jsonWriter.write(obj)
    }
  }
}
