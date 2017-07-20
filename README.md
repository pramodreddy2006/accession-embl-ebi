### What is this repository for? ###

You are given a list of comma separated accession numbers as input.

An accession number has the following format: L...LN...N (e.g. AB1234)

where L...L denotes one or more ASCII7 letters and N denotes one or more digits (0,1,2,3,4,5,6,7,8 or 9).

Please return an ordered list of accession numbers where any consecutive digits N...N that share the same prefix L...L are collapsed into an accession number range.

An accession number range has the following format : L...LN...N-L...LN...N (e.g. A00001-A00005).

Please note that the N...N digits are left padded using 0s (e.g. 00001) and that the length of the N...N digits must be the same for accession numbers to be part of the same accession number range.

Example input:
A00000, A0001, ERR000111, ERR000112, ERR000113, ERR000115, ERR000116, ERR100114, ERR200000001, ERR200000002, ERR200000003, DRR2110012, SRR211001, ABCDEFG1

Expected output:
A00000, A0001, ABCDEFG1, DRR2110012, ERR000111-ERR000113, ERR000115-ERR000116, ERR100114, ERR200000001-ERR200000003, SRR211001 



### How do I get set up? ###

* Maven commands
cd path-to-accession-embl-ebi
mvn clean install


### Command line ###
cd path-to-accession-embl-ebi/target
java -jar  accession.jar
A0001, A0002

A0001-A0002


### WEB REST API ###
cd path-to-accession-embl-ebi/target
java -jar  accession-rest.jar


POST http://localhost:8080/accession HTTP/1.1
Accept: application/json
Content-Type: application/json

A0001, A0002

Output:
["A0001-A0002"]


POST http://localhost:8080/accession/array HTTP/1.1
Accept: application/json
Content-Type: application/json

["A0001", "A0002"]

Output:
["A0001-A0002"]




