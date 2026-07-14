function validateForm(){
    let name=document.getElementById("name").value.trim();
    let email=document.getElementById("email").value.trim();
    let cata=document.getElementById("category").value.trim();
    let desc=document.getElementById("desc").value.trim();
    
    if(name===""){
        alert("Name cannot be empty");
        return false;
    }
    const name_check=/^[A-Za-z]+$/;
    if(!name_check.test(name)){
        alert("Name should contain only letters");
        return false;
    }
    if(email===""){
        alert("Email cannot be empty");
        return false;
    }
    const email_check=/^[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)?@[a-zA-Z0-9]+(\.[a-zA-Z]{2,})(\.[a-zA-Z]{2,})?$/;
    if(!email_check.test(email)){
        alert("Email format is invalid");
        return false;
    }
    if(cata===""){
        alert("Category cannot be empty");
        return false;
    }
    if(desc===""){
        alert("Description cannot be empty");
        return false;
    }
    if(desc.length < 2){
        alert("Description cannot be less than 2 letters");
        return false;
    }
    if(desc.length > 500){
        alert("Description cannot be greater than 500 letters");
        return false;
    }
    return true;
}