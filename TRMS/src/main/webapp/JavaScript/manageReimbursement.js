

window.onload = ()=>{
    var coll = document.getElementsByClassName("collapsible");
var i;
console.log(coll)
if(coll){
    console.log(coll)
    for(i = 0; i<coll.length; i++){
        coll[i].addEventListener("click",function() {
            this.classList.toggle("active")
            var c = this.nextElementSibling
            console.log(c)
            if(c.style.display==="block"){
                c.style.display = "none"
            }else{
                c.style.display = "block"
            }
        })
    }
    
}
}