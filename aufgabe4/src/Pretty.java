public interface Pretty {
  // "transform" transforms a piece of Java
  // source code into equivalent code with
  // (possibly) prettier formatting.
  // "transform(x)+transform(y)" shall return
  // the same string as "transform(x+y)" for
  // all strings x and y.
  String transform (String s);
  // "reset" indicates the end of source code.
  // The internal state is reset (if there is
  // one), and the argument of the next
  // invocation of transform represents the
  // begin of another source code.
  void reset();
}
