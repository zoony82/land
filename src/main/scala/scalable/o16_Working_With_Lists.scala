package scalable

object o16_Working_With_Lists extends App{
  println("16.1 Lists literals")
  val fruit = List("apples", "oranges", "pears")
  val nums = List(1,2,3,4)
  val diag4 =
    List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )
  val empty: List[Nothing] = List()

  println("16.2 The List type")

  println("16.3 Construction lists")
  "pears" :: Nil
  "oranges" :: ("pears" :: Nil)
  "apples" :: ("oranges" :: ("pears" :: Nil))
  Nil
  1 :: 2 :: 3 :: 4 :: Nil

  println("Basic operations on lists")
  Nil.head
  def isort(xs: List[Int]):List[Int] ={
    if(xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))
  }

  def insert(x:Int, xs : List[Int]) : List[Int] ={
    if(xs.isEmpty || x <= xs.head) x :: xs
    else xs.head::insert(x, xs.tail)


  }

  val abc = 1 :: 2 :: 10 :: 4 :: Nil
  val abcOrded = isort(abc)



}
