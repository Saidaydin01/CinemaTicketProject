function kjopbillet() {
    let feilteller = 0;

    // Validate film selection
    const innFilm = $("#film").val();
    if (!innFilm || innFilm === "Velg film her") {
        feilteller++;
        $("#div1").text("Velg en film").css("color", "red");
    } else {
        $("#div1").text("");
    }

    // Validate number of tickets
    const innAntall = $("#antall").val();
    if (!innAntall || innAntall <= 0) {
        feilteller++;
        $("#div2").text("Oppgi et gyldig antall").css("color", "red");
    } else {
        $("#div2").text("");
    }

    // Validate first name
    const innFornavn = $("#fornavn").val();
    if (!innFornavn.trim()) {
        feilteller++;
        $("#div3").text("Oppgi fornavn").css("color", "red");
    } else {
        $("#div3").text("");
    }

    // Validate last name
    const innEtternavn = $("#etternavn").val();
    if (!innEtternavn.trim()) {
        feilteller++;
        $("#div4").text("Oppgi etternavn").css("color", "red");
    } else {
        $("#div4").text("");
    }

    // Validate phone
    const innTelefon = $("#telefon").val();
    if (!/^\+?\d{8,15}$/.test(innTelefon) || innTelefon.length < 8) {
        feilteller++;
        $("#div5").text("Oppgi et gyldig telefonnummer").css("color", "red");
    } else {
        $("#div5").text("");
    }

    // Validate email
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const innEpost = $("#epost").val();
    if (!emailRegex.test(innEpost)) {
        feilteller++;
        $("#div6").text("Ugyldig e-postadresse").css("color", "red");
    } else {
        $("#div6").text("");
    }

    // Submit if no errors
    if (feilteller === 0) {
        const billett = {
            film: innFilm,
            antall: innAntall,
            fornavn: innFornavn,
            etternavn: innEtternavn,
            telefon: innTelefon,
            epost: innEpost
        };

        $.ajax({
            url: "/lagre",
            type: "POST",
            contentType: "application/json", // Specify the correct content type
            data: JSON.stringify(billett),   // Convert the JavaScript object to JSON
            success: function () {
                hentAlle(); // Refresh the table
            },
            error: function (xhr, status, error) {
                console.error("Error saving the ticket:", xhr.responseText || error);
            }
        });


        sletter(); // Reset form
    }
}

function hentAlle() {
    $.get("/hentAlle", function (data) {
        formater(data);
    });
}

function formater(billetter) {
    let ut = `<table class="table table-striped mt-4">
                <thead>
                    <tr>
                        <th>Filmnavn</th><th>Antall</th><th>Navn</th><th>Etternavn</th>
                        <th>Telefon</th><th>E-post</th>
                    </tr>
                </thead>
                <tbody>`;
    for (let b of billetter) {
        ut += `<tr>
                   <td>${b.film}</td><td>${b.antall}</td><td>${b.fornavn}</td>
                   <td>${b.etternavn}</td><td>${b.telefon}</td><td>${b.epost}</td>
               </tr>`;
    }
    ut += `</tbody></table>`;
    $("#skriv").html(ut);
}

function sletter() {
    $("#film").val("Velg film her");
    $("#antall").val("");
    $("#fornavn").val("");
    $("#etternavn").val("");
    $("#telefon").val("");
    $("#epost").val("");
}

function slettAlle() {
    $.get("/slettAlle", function () {
        hentAlle();
    });
}
