package io.lvivscalaclub.simplejson

trait JsonWriter[-A] {
  def write(obj: A): String
}
