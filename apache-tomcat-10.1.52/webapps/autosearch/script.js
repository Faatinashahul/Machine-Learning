function fetchCountries() {
    let keyword = document.getElementById("country").value;
    if (keyword.length === 0) {
        document.getElementById("suggestions").innerHTML = "";
        return;
    }

    let xhr = new XMLHttpRequest();
    xhr.open("GET", "CountryServlet?keyword=" + keyword, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let suggestionBox = document.getElementById("suggestions");
            suggestionBox.innerHTML = "";
            data.forEach(function (country) {
                let div = document.createElement("div");
                div.className = "item";
                div.innerHTML = country;
                div.onclick = function () {
                    document.getElementById("country").value = this.innerHTML;
                    suggestionBox.innerHTML = "";
                };
                suggestionBox.appendChild(div);
            });
        }
    };

    xhr.send();
}