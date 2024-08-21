function eliminar(id, modulo) {
    swal({
        title: "Esta seguro?",
        text: "Una vez eliminado, ¡no podrá recuperar este registro!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
            .then((willDelete) => {
                if (willDelete) {
                    swal("¡Su registro ha sido eliminado!!", {
                        icon: "success",
                    }).then((willDelete) => {
                        window.location.href = "/delete" + modulo + "/" + id;
                        console.log("Ultimo OK");
                    });
                }
            });
}

var arr_items = [];

var arr_items_del = [];

function add(item) {

    item.total = 0;

    var flat = false;

    for (var i = 0; i < arr_items.length; i++) {
        var data = arr_items[i];
        console.log(item, data, i);
        if (item.id === data.id) {
            flat = true;

            document.getElementById("myCant" + (i + 1)).value = parseInt(document.getElementById("myCant" + (i + 1)).value) + 1;

            cal_total_item(arr_items.length);
        }
    }

    if (!flat) {
        arr_items.push(item);
        create_row(arr_items, item);
    }
}

function create_row(arr_items, item) {
    var table = document.getElementById("myTable");

    var row = table.insertRow(arr_items.length - 1);
    row.id = "row" + arr_items.length;

    var name = row.insertCell(0);
    var cant = row.insertCell(1);
    var price = row.insertCell(2);
    var total = row.insertCell(3);
    total.id = "td_total_" + arr_items.length;
    var del = row.insertCell(4);


    name.innerHTML = item.name;

    cant.innerHTML = '<input type="number" value="0" id="myCant' + arr_items.length + '" onchange="cal_total_item(' + arr_items.length + ')" class="form-control">';

    price.innerHTML = item.price;
    price.class = "text-right";

    total.innerHTML = 0;
    total.class = "text-right";


    del.innerHTML = '<button type="button" class="btn btn-danger" onclick="delete_item(' + arr_items.length + ',this)">Eliminar</button>';


}


function delete_item(item, x) {

    var uri = window.location.pathname;



    if (uri.includes("updateSale")) {
        arr_items_del.push(arr_items[item - 1]);
    }

    console.log(arr_items);

    arr_items.splice(arr_items[item - 1], 1);

    $('#row' + item).remove();



    cal_total_gen();


}


function cal_total_item(item) {

    var table = document.getElementById("td_total_" + item);

    var valor = parseInt(document.getElementById("myCant" + item).value);

    var total = valor * arr_items[item - 1].price;

    arr_items[item - 1].total = total;

    arr_items[item - 1].cant = parseInt(valor);

    table.innerHTML = total;

    var stock = arr_items[item - 1].stock;

    if (stock == undefined) {
        stock = arr_items[item - 1]["product"].stock;
        stock = stock + arr_items[item - 1].quantity_item;

    }

    if (valor > stock) {
        document.getElementById("save_sale1").disabled = true;
        document.getElementById("save_sale").disabled = true;
        swal("El producto supera el stock permitido !");
    } else {
        document.getElementById("save_sale1").disabled = false;
        document.getElementById("save_sale").disabled = false;
    }

    cal_total_gen();
}

function cal_total_gen() {

    var total = document.getElementById("total_general");
    var iva = document.getElementById("iva_general");
    var sub_total = document.getElementById("sub_total_general");

    var tasa_iva = (parseFloat(document.getElementById("iva_venta").value) / 100)

    var total_general = 0;
    for (var i in arr_items) {
        total_general = total_general + arr_items[i].total;
    }

    document.getElementById("total_general_hidden").value = (total_general + (total_general * tasa_iva));
    document.getElementById("iva_general_hidden").value = total_general * tasa_iva;
    document.getElementById("sub_total_hidden").value = total_general;

    total.innerHTML = total_general + (total_general * tasa_iva);
    iva.innerHTML = total_general * tasa_iva;
    sub_total.innerHTML = total_general;



    var myJSON = JSON.stringify(arr_items);
    var myJSON_DEL = JSON.stringify(arr_items_del);
    console.log(myJSON);
    console.log(myJSON_DEL);
    document.getElementById("arr_items").value = myJSON;
    document.getElementById("arr_items_del").value = myJSON_DEL;


}


$(window).on("load", function () {

    var pathname = window.location.pathname;

    if (pathname.includes("updateSale")) {

        var myJSON = JSON.parse(document.getElementById("arr_items").value);

        for (var i = 0; i < myJSON.length; i++) {

            myJSON[i].name = myJSON[i].name_item;
            myJSON[i].price = myJSON[i].price_item;
            myJSON[i].total = myJSON[i].total_item;

            arr_items.push(myJSON[i]);

            create_row(arr_items, myJSON[i]);

            document.getElementById("myCant" + (i + 1)).value = myJSON[i].quantity_item;

            cal_total_item(i + 1);
        }
    }

});

