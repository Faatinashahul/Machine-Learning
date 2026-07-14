document.addEventListener("DOMContentLoaded", function () {
    const salutation = document.getElementsByName("sal");
    const name = document.getElementById("name");
    const uname = document.getElementById("uname");
    const password = document.getElementById("password");
    const email = document.getElementById("email");
    const dob = document.getElementById("dob");
    const photo = document.getElementById("photo");
    const langs = document.getElementsByName("lang");
    const otherLang = document.getElementById("otherLang");
    const otherText = document.getElementById("otherText");
    const expertise = document.getElementById("expertise");
    const form = document.getElementById("registration-form");

    salutation.forEach(radio => {
        radio.addEventListener("change", () => {
            showSuccess("salmsg");
            name.focus();
        });
    });


    name.addEventListener("focus", () => {
        safeShowHint("namemsg", "Format: S. Firstname Lastname");

    });

    name.addEventListener("blur", () => {
        let pattern = /^[A-Z]\.\s[A-Za-z]+(\s[A-Za-z]+)?$/;
        validateField(name, pattern, "namemsg", uname, "Invalid name format");
    });

    uname.addEventListener("focus", () => {
        safeShowHint("unamemsg", "Only alphabets allowed");
    });

    uname.addEventListener("blur", () => {
        let pattern = /^[A-Za-z]+$/;
        validateField(uname, pattern, "unamemsg", password, "Invalid username");
    });

    password.addEventListener("focus", () => {
        safeShowHint("passmsg", "Min 8 chars, A-Z, a-z, number, special char");
    });

    password.addEventListener("blur", () => {
        let pattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*-]).{8,}$/;
        validateField(password, pattern, "passmsg", email, "Weak password");
    });

    email.addEventListener("focus", () => {
        safeShowHint("emailmsg", "example@domain.com");
    });

    email.addEventListener("blur", () => {
        let pattern = /^[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)?@[a-zA-Z0-9]+\.[a-zA-Z]{2,}(\.[a-zA-Z]{2,})?$/;
        validateField(email, pattern, "emailmsg", dob, "Invalid email");
    });

    dob.addEventListener("blur", () => {
        let birth = new Date(dob.value);
        let age = new Date().getFullYear() - birth.getFullYear();

        if (age < 18 || age > 35) {
            showError("dobmsg", "Age must be 18–35");
            dob.value = "";
            dob.focus();
        } else {
            showSuccess("dobmsg");
            photo.focus();
        }
    });

    photo.addEventListener("change", () => {
        if (photo.value === "") {
            showError("photomsg", "Upload photo");
            photo.focus();
        } else {
            showSuccess("photomsg");
        }
    });

    langs.forEach(chk => {
        chk.addEventListener("change", () => {
            checkLanguages();
        });
    });

    otherLang.addEventListener("change", () => {
        if (otherLang.checked) {
            otherText.style.display = "inline";
            otherText.focus();
        } else {
            otherText.style.display = "none";
            otherText.value = "";
        }
    });

    otherText.addEventListener("blur", () => {
        if (otherLang.checked && otherText.value.trim() === "") {
            showError("langmsg", "Specify other language");
            otherText.focus();
        } else {
            showSuccess("langmsg");
            expertise.focus();
        }
    });

    expertise.addEventListener("focus", () => {
        safeShowHint("expertisemsg", "Enter your skills");
    });

    expertise.addEventListener("blur", () => {
        if (expertise.value.trim() === "") {
            showError("expertisemsg", "Expertise required");
            expertise.focus();
        } else {
            showSuccess("expertisemsg");
        }
    });

    form.addEventListener("submit", function (e) {
        e.preventDefault();
        alert("Details have been submitted Successfully");
    });
});

function safeShowHint(msgId, hintText) {
    const msg = document.getElementById(msgId);
    if (!msg.classList.contains("error")) {
        showHint(msgId, hintText);
    }
}
function showHint(id, msg) { 
    let span = document.getElementById(id); 
    span.textContent = msg; 
    span.className = "msg hint"; }

function showError(id, msg) {
    let span = document.getElementById(id);
    span.textContent = msg;
    span.className = "msg error";
}

function showSuccess(id) {
    let span = document.getElementById(id);
    span.textContent = "Valid";
    span.className = "msg success";
}

function validateField(field, pattern, msgId, nextField, errorMsg) {
    if (!pattern.test(field.value.trim())) {
        showError(msgId, errorMsg);
        field.value = "";
        field.focus();
        return false;
    }
    showSuccess(msgId);
    if (nextField) {
        nextField.focus();
    }

    return true;
}




function checkLanguages() {
    let selected = document.querySelectorAll('input[name="lang"]:checked');
    if (selected.length < 2) {
        showError("langmsg", "Select at least two languages");
    } else {
        showSuccess("langmsg");
    }
}
