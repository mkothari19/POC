package mk.scala.caseobject
/*
 * A case object is like an object, but just like a case class has more features than a regular class, 
 * a case object has more features than a regular object. Its features include:

It’s serializable
It has a default hashCode implementation
It has an improved toString implementation
Because of these features, case objects are primarily used in two places (instead of regular objects):

When creating enumerations
When creating containers for “messages” that you want to pass between other objects (such as with the Akka actors library)
 */
sealed trait Topping
case object Cheese extends Topping
case object Pepperoni extends Topping
case object Sausage extends Topping
case object Mushrooms extends Topping
case object Onions extends Topping

sealed trait CrustSize
case object SmallCrustSize extends CrustSize
case object MediumCrustSize extends CrustSize
case object LargeCrustSize extends CrustSize

sealed trait CrustType
case object RegularCrustType extends CrustType
case object ThinCrustType extends CrustType
case object ThickCrustType extends CrustType

case class Pizza (
    crustSize: CrustSize,
    crustType: CrustType,
    toppings: Seq[Topping]
)
/*
 * PassMessage message to other oject
 */
case class StartSpeakingMessage(textToSpeak: String)
case object StopSpeakingMessage
case object PauseSpeakingMessage
case object ResumeSpeakingMessage

/*class Speak extends Actor {
  def receive = {
    case StartSpeakingMessage(textToSpeak) =>
        // code to speak the text
    case StopSpeakingMessage =>
        // code to stop speaking
    case PauseSpeakingMessage =>
        // code to pause speaking
    case ResumeSpeakingMessage =>
        // code to resume speaking
  }*/
