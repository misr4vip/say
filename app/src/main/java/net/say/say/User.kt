package net.say.say

 class User {

     var userId : String = ""
     var userFirstName : String  =""
     var userLastName : String= ""
     var userEmail : String= ""
     var userPassword : String= ""
     var userIsUserAuth : Boolean= false


   constructor( id: String,firstname : String, lastName : String , email : String,pwd : String , isAuth : Boolean) {

       this.userId = id
       this.userFirstName = firstname
       this.userLastName = lastName
       this.userEmail = email
       this.userPassword = pwd
       this.userIsUserAuth = isAuth

   }


     companion object {
         var Id : String =""
         var FirstName : String= ""
         var LastName : String= ""
         var Email : String= ""
         var Password : String= ""
         var IsUserAuth : Boolean= false
     }
 }



