const xlsx = require("xlsx");

var wb = xlsx.readFile("Games.xlsx");
var ws = wb.Sheets["PlayingInfo"];

var data = xlsx.utils.sheet_to_json(ws);

var posR=0,negR=0;
var arr = {};

for(let i=0;i<data.length;i++){
    if(data[i].PlayGame == "Yes")
    {   
        posR++;
    }else if(data[i].PlayGame == "No"){
        negR++;
    }

    if(!(data[i].Wind in arr)){
        arr[data[i].Wind] = 0;
    }
    
    arr[data[i].Wind]++;
}

// console.log(arr);
// console.log(Object.keys(arr).length);


let arr2 = {};

for(let i=0;i<data.length;i++){

    if(!(data[i].Wind in arr2)){
        // console.log("Not present");
        arr2[data[i].Wind] = {"Yes":0,"No":0};
    }

    var res = data[i].PlayGame;
    // console.log(arr2[data[i].Wind].Yes);
    if(res == "Yes"){
        arr2[data[i].Wind].Yes++;
    }
    else if(res == "No"){
        arr2[data[i].Wind].No++;
    }
}

// console.log(arr2);

var totRecord = data.length;
var entropy = -((posR/totRecord)*(Math.log2(posR/totRecord))) - ((negR/totRecord)*(Math.log2(negR/totRecord)));

// console.log(entropy);

for(const key in arr2){
    var array = arr2[key];
    // console.log(array);

    let posRes = array.Yes,negRes = array.No;
    let totRes = posRes + negRes;
    // console.log(posRes + " " + negRes);

    let ent = -((posRes/totRes)*(Math.log2(posRes/totRes))) - ((negRes/totRes)*(Math.log2(negRes/totRes)));
    arr2[key].ent = ent;
    // console.log(arr2[key]);
}

// console.log(arr2);

var ent_sum = 0;

for(const key in arr2){
    let rec = arr2[key].Yes + arr2[key].No;
    // console.log(rec);

    ent_sum += (rec/totRecord)*(arr2[key].ent);
    // console.log(arr2[key].ent);
}

// console.log("Ent Sum : " + ent_sum);
var infogain = entropy - ent_sum;
infogain = infogain.toPrecision(4);
console.log("Info Gain(S,Wind) :" ,infogain);