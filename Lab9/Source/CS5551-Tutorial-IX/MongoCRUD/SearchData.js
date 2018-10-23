/*
 * Created by mnpw3d on 20/10/2016.
 */

var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://root:Saitejaswi1997@ds137703.mlab.com:37703/ase_lab9';
var findUser = function(db, callback) {
    var cursor =db.collection('ase_lab9').find( );
    cursor.each(function(err, doc) {
        assert.equal(err, null);
        if (doc != null) {
            console.log(doc);
        } else {
            callback();
        }
    });
};
var findUserwithName = function(db,callback) {
    var cursor = db.collection('ase_lab9').find({"fname":"Sai Tejaswi"});
    cursor.each(function(err,doc) {
        assert.equal(err,null);
        if(doc != null)
        {
            console.log("First Name:" + doc.fname);
            console.log("Last Name:" + doc.lname);
            console.log("city:" + doc.address.city);
        }
    });
}
var findUserwithUniversity = function(db, callback) {
    var cursor = db.collection('ase_lab9').find({"education.university":"UMKC"});
    cursor.each(function(err,doc){
       assert.equal(err,null);
       if(doc != null)
       {
           console.log("First Name:" + doc.fname);
           console.log("University:" + doc.education.university);
           console.log("Degree:" + doc.education.degree);
           console.log("Major:" + doc.education.major);
           console.log("mail:" + doc.mail);
       }
    });
}

var findUserwithMajor = function(db, callback) {
    var cursor = db.collection('ase_lab9').find({"education.major":"Computer Science"});
    cursor.each(function(err,doc){
        assert.equal(err,null);
        if(doc != null)
        {
            console.log("First Name:" + doc.fname);
            console.log("University:" + doc.education.university);
            console.log("Degree:" + doc.education.degree);
            console.log("Major:" + doc.education.major);
            console.log("mail:" + doc.mail);
        }
    });
}

MongoClient.connect(url, function(err, client) {
    if (err) throw err;
    var db = client.db('ase_lab9');
    findUserwithMajor(db, function() {
        db.close();
    });
});