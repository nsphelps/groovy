import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import java.nio.ByteBuffer
import java.nio.charsets.StandardCharsets

class Header {
    // Header fields
    int val1        // First two bytes
    byte val2       // 1 byte
    char val3       // 1 byte
    int val4        // 4 bytes
    int val5        // 4 bytes
    int val6        // 4 bytes
    double val7     // 8 bytes
    double val8     // 8 bytes
    boolean val9    // 1 byte
    short val10     // 2 bytes
    char val11      // 1 byte
    char val12      // 1 byte

    // Constructor
    Header (int val1, byte val2, char val3, int val4, int val5, int val6, double val7, double val8, boolean val9, short val10, char val11, char val12) {
        this.val1 = val1
        this.val2 = val2
        this.val3 = val3
        this.val4 = val4
        this.val5 = val5
        this.val6 = val6
        this.val7 = val7
        this.val8 = val8
        this.val9 = val9
        this.val10 = val10
        this.val11 = val11
        this.val12 = val12
    }

    // Method to convert to JSON
    def toJson() {
        return JsonOutput.toJson(this)
    }
}

// Function to parse binary data into header object
def parseHeader(ByteBuffer binaryBuffer) {
    ByteBuffer buffer = binaryBuffer
    
    int val1 = buffer.getShort()
    byte val2 = buffer.get()
    char val3 = buffer.get()
    int val4 = buffer.getInt()
    int val5 = buffer.getInt()
    int val6 = buffer.getInt()
    double val7 = buffer.getDouble()
    double val8 = buffer.getDouble()
    boolean val9 = buffer.get() != 0
    short val10 = buffer.getShort()
    char val11 = buffer.getChar()
    char val12 = buffer.getChar()

    return new Header(val1, val2, val3, val4, val5, val6, val7, val8, val9, val10, val11, val12)
}


class MsgBody {
    // Define fields for message body
    int val13		// 4 bytes
    int val14		// 4 bytes
    int val15		// 4 bytes
    int val16   	// 2 bytes
    int val17		// 2 bytes
    String val18	// 10 bytes, first store 10 bytes in new byte array, convert to string
    int val19 		// 2 bytes
    int val20		// 4 bytes
    int val21		// 4 bytes, float
    int val22		// 4 bytes
    int val23		// 4 bytes, float
    int val24		// 4 bytes
    int val25 		// 4 bytes, float
    int val26		// 4 bytes
    int val27_x     // val27 is 3 64-bit values that have the structure x,y,z
    int val27_y     //
    int val27_z     //
    int val28 		// 24 bytes, float, 3x 64-bit values (x, y, z)
    int val29 		// 24 bytes, float, 3x 64-bit values (x, y, z)
    int val30 		// 180 bytes, float, 45 32-bit values (1,1), (1, 2)...
    int val31		// 2 bytes
    int val32 		// 2 bytes
    int val33		// 4 bytes
    int val34		// 4 bytes
    int val35 		// 4 bytes, float
    int val36		// 4 bytes
    int val37		// 24 bytes, float, 3 64-bit values (x,y,z)
    int val38   	// 24 bytes, float, 3 64-bit values (x,y,z)
    int val39		// 84 bytes, float, 21 32-bit values
    int val40 		// 2 bytes
    int val41		// 2 bytes
    int val42		// 4 bytes
    int val43		// 4 bytes, float
    int val44   	// 4 bytes, float
    int val45		// 4 bytes, float
    int val46 		// 4 bytes, float
    int val47 		// 4 bytes, float
    int val48 		// 4 bytes, float
    int val49 		// 4 bytes, float
    int val50 		// 4 bytes, float
    int val51 		// 4 bytes, float
    int val52		// 2 bytes
    int val53		// 2 bytes
    int val54		// 4 bytes
    int val55 		// 4 bytes, float
    int val56 		// 4 bytes, float
    int val57 		// 4 bytes, float
    int val58 		// 4 bytes, float
    int val59 		// 4 bytes, float
    int val60 		// 4 bytes, float
    int val61 		// 4 bytes, float
    int val62		// 2 bytes
    int val63		// 2 bytes
    int val64		// 2 bytes
    int val65		// 2 bytes
    int val66		// 2 bytes
    int val67		// 2 bytes
    int val68		// 2 bytes
    int val69		// 2 bytes
    int val70		// 4 bytes, float
    int val71		// 4 bytes, float
    int val72		// 4 bytes, float
    int val73		// 4 bytes, float
    int val74		// 4 bytes, float
    int val75		// 4 bytes, float
    int val76		// 2 bytes
    int val77		// 2 bytes
    int val78		// 2 bytes
    int val79		// 2 bytes
    int val80		// 4 bytes
    int val81		// 4 bytes
    int val82		// 4 bytes
    int val83		// 4 bytes
    int val84		// 4 bytes, float
    int val85		// 4 bytes, float
    int val86		// 4 bytes, float
    int val87		// 4 bytes, float
    int val88		// 4 bytes
    int val89		// 8 bytes, float
    int val90		// 8 bytes, float
    int val91		// 8 bytes, float
    int val92		// 8 bytes, float

    // Constructor
    MsgBody(int val13...val92) {   //FIXME: Populate the rest of the constructor
        this.val13 = val13
	this.val14 = val14
	this.val15 = val15
	this.val16 = val16
	//FIXME: Populate the rest of the constructor
    }

    // Method to convert to JSON
    def toJson() {
        return JsonOutput.toJson(this)
    }
}

// Function to parse binary data into message body object
def parseMsgBody(byte[] binaryBuffer) {
    // Parse binary buffer to extract message body data and initialize message body object
    // Return the initialized message body object
    ByteBuffer buffer = ByteBuffer.wrap(binaryBuffer) 
    buffer.position(41)   // FIXME: Not actually sure if this is the correct offset?
    
    //FIXME: These need to have their respective " buffer.get*" commands added. 
    int val13
    int val14
    int val15
    int val16  
    int val17

    // Strings need to be handled differently because of the length
    byte val18_bytes = new byte[10]
    buffer.get(val18_bytes)
    String val18 = new String(val18_bytes, StandardCharsets.US_ASCII)

    int val19
    int val20
    int val21
    int val22
    int val23
    int val24
    int val25
    int val26
    int val27_x = buffer.getDouble() // 8 bytes
    int val27_y = buffer.getDouble() // 8 bytes
    int val27_z = buffer.getDouble() // 8 bytes
    int val28
    int val29
    int val30
    int val31
    int val32
    int val33
    int val34
    int val35 
    int val36
    int val37
    int val38
    int val39
    int val40
    int val41
    int val42
    int val43
    int val44
    int val45
    int val46 
    int val47 
    int val48 	
    int val49 	
    int val50 	
    int val51 	
    int val52	
    int val53	
    int val54	
    int val55 	
    int val56 	
    int val57 	
    int val58 	
    int val59 	
    int val60 	
    int val61 	
    int val62	
    int val63	
    int val64	
    int val65	
    int val66	
    int val67	
    int val68	
    int val69	
    int val70	
    int val71	
    int val72	
    int val73	
    int val74	
    int val75	
    int val76	
    int val77	
    int val78	
    int val79	
    int val80	
    int val81	
    int val82 = buffer.getInt()
    int val83 = buffer.getInt()
    int val84
    int val85
    int val86
    int val87
    int val88
    int val89
    int val90
    int val91
    int val92

    return new MsgBody(val13...val92)
}

// Function to merge JSON objects
def mergeJson(String json1, String json2) {
    def map1 = new JsonSlurper().parseText(json1)
    def map2 = new JsonSlurper().parseText(json2)
    
    // Merge maps by concatenating them
    def mergedMap = map1 + map2
    
    // Convert merged map back to JSON
    return new JsonOutput().toJson(mergedMap)
}

// Parse binary data into header and message body objects
def header = parseHeader(binaryBuffer)
def msgBody = parseMsgBody(binaryBuffer)

// Convert header and message body objects to JSON strings
def jsonHeader = header.toJson()
def jsonMsgBody = msgBody.toJson()

// Merge JSON objects into one larger JSON object
def mergedJson = mergeJson(jsonHeader, jsonMsgBody)

println JsonOutput.prettyPrint(mergedJson)
