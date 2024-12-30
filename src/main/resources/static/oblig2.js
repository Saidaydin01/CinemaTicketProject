function kjopbillet() {

    let feilteller = 0;
    let innFilm = $("#film").val();
    if ((innFilm === "") || (innFilm === "Velg film her")) {
        feilteller++;
        $("#div1").html("velg en film");
    }

    let innAntall = $("#antall").val();
    if ((innAntall === 0) || (innAntall === "")) {
        feilteller++;
        let ut = "Skriv inn antall billett";
        $("#div2").html(ut).css("color", "red");
    }

    let innFornavn = $("#fornavn").val();
    if (innFornavn === "") {
        feilteller++;
        let ut = "Skriv inn fornavn";
        $("#div3").html(ut).css("color", "red");
    }

    let innEtternavn = $("#etternavn").val();
    if (innEtternavn === "") {
        feilteller++;
        let ut = "Skriv inn eternavn";
        $("#div4").html(ut).css("color", "red");
    }

    let innTelefon = $("#telefon").val();
    if (innTelefon === "") {
        feilteller++;
        let ut = "Skriv inn telefonnummer";
        $("#div5").html(ut).css("color", "red");
    }

    let innEpost= $("#epost").val();
    if (innEpost === "") {
        feilteller++;
        let ut = "Skriv inn epost";
        $("#div6").html(ut).css("color", "red");
    }

    if (feilteller === 0) {
        const billett = {
            film: innFilm,
            antall: innAntall,
            fornavn: innFornavn,
            etternavn: innEtternavn,
            telefon: innTelefon,
            epost:innEpost
        };

        $.post("/lagre", billett, function () {
            hentAlle();
            });
        sletter();
    }


}

function hentAlle() {
    $.get("/hentAlle", function (data) {
        formater(data)
    });
}

function formater(billetter) {

    let ut = "<table class='table table-striped mt-4'><tr><th>Filmnavn</th><th>Antall</th><th>Navn</th><th>Etternavn</th>" +
        "<th>Telefon</th><th>Epost</th></tr>";


    for (let b of billetter) {
        ut += "<tr><td>" + b.film + "</td><td>" + b.antall + "</td><td>" + b.fornavn + "</td><td>" + b.etternavn +
            "</td><td>" + b.telefon + "</td><td>" + b.epost + "</td></tr>"
    }
    ut += "</table>"
    $("#skriv").html(ut)
}
function sletter() {
    $("#film").val("Velg en film")
    $("#antall").val("")
    $("#fornavn").val("")
    $("#etternavn").val("")
    $("#telefon").val("")
    $("#epost").val("")
}

function slettAlle() {
    $.get("/slettAlle", function () {
        hentAlle();
    });
}

