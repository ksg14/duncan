/// Dependencies
var stanfordClassifier = require('stanford-classifier');
var byline = require('byline');
var fs = require('fs');

/// Initialize the Stanford Classifier
var sc = new stanfordClassifier();

var readline = require('readline');
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

/*rl.on('line', function(line) {
    //console.log(line);
})
*/

var mem = [];

/// Create a stream to read the dataset
var stream = byline(fs.createReadStream('trainingSet.txt', {
    encoding: 'utf8'
}));

/// Push each line into memory
stream.on('data', function(line) {
    mem.push(line);
});

/// Use the training dataset in memory to train the classifier dataset
stream.on('end', function() {
    for (var i = 0; i < mem.length; i++) {
        var line = mem[i];
        sc.train(line);
    }

    /// Sync the classifier with the classifiers dataset
    sc.syncClassifier();

    /// Use the classifier
    rl.on('line', function(line) {
        console.log(sc.classify(line));
        //console.log(line);
    })

    /// BAND
});