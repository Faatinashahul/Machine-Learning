function validate(){
    var student_name=document.getElementById("name").value.trim();
    var ans1=document.getElementsByName("q1").value;
    var ans2=document.getElementsByName("q2").value;
    var q1="Modi";
    var q2="Hindhi";
    var score=0;
    if(student_name===""){
        alert("Student cant be empty");
        return false;
    }
    for(var i=1;i<=2;i++){
        var check=document.querySelector('input[name="q'+i+'"]:checked');
        if(check===null){
            alert("please answer all submissions");
            return false;
        }
    }
    alert("evaluated");
    if(ans1===q1){
        score++;
    }
    if(ans2===q2){
        score++;
    }
    if(score===2){
        alert("excellent");
    }
    else{
        alert("failed");
    }
    
    return true;

}