
package io.gamemachine.client.messages;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;

import io.protostuff.ByteString;
import io.protostuff.GraphIOUtil;
import io.protostuff.Input;
import io.protostuff.Message;
import io.protostuff.Output;
import io.protostuff.ProtobufOutput;

import java.io.ByteArrayOutputStream;
import io.protostuff.JsonIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;

import io.gamemachine.util.LocalLinkedBuffer;


import java.nio.charset.Charset;


import io.protostuff.Schema;
import io.protostuff.UninitializedMessageException;


@SuppressWarnings("unused")
public final class Polygon implements Externalizable, Message<Polygon>, Schema<Polygon>{



    public static Schema<Polygon> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Polygon getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Polygon DEFAULT_INSTANCE = new Polygon();

        public List<Vector3> vertex;
	  
    public Polygon()
    {
        
    }


	

	    
    public Boolean hasVertex()  {
        return vertex == null ? false : true;
    }
        
		public List<Vector3> getVertexList() {
		if(this.vertex == null)
            this.vertex = new ArrayList<Vector3>();
		return vertex;
	}

	public Polygon setVertexList(List<Vector3> vertex) {
		this.vertex = vertex;
		return this;
	}

	public Vector3 getVertex(int index)  {
        return vertex == null ? null : vertex.get(index);
    }

    public int getVertexCount()  {
        return vertex == null ? 0 : vertex.size();
    }

    public Polygon addVertex(Vector3 vertex)  {
        if(this.vertex == null)
            this.vertex = new ArrayList<Vector3>();
        this.vertex.add(vertex);
        return this;
    }
            	    	    	    	
    public Polygon removeVertexByX(Vector3 vertex)  {
    	if(this.vertex == null)
           return this;
            
       	Iterator<Vector3> itr = this.vertex.iterator();
       	while (itr.hasNext()) {
    	Vector3 obj = itr.next();
    	
    	    		if (vertex.x.equals(obj.x)) {
    	      			itr.remove();
    		}
		}
        return this;
    }
    
        	    	    	    	
    public Polygon removeVertexByY(Vector3 vertex)  {
    	if(this.vertex == null)
           return this;
            
       	Iterator<Vector3> itr = this.vertex.iterator();
       	while (itr.hasNext()) {
    	Vector3 obj = itr.next();
    	
    	    		if (vertex.y.equals(obj.y)) {
    	      			itr.remove();
    		}
		}
        return this;
    }
    
        	    	    	    	
    public Polygon removeVertexByZ(Vector3 vertex)  {
    	if(this.vertex == null)
           return this;
            
       	Iterator<Vector3> itr = this.vertex.iterator();
       	while (itr.hasNext()) {
    	Vector3 obj = itr.next();
    	
    	    		if (vertex.z.equals(obj.z)) {
    	      			itr.remove();
    		}
		}
        return this;
    }
    
        	    	    	    	
    public Polygon removeVertexByXi(Vector3 vertex)  {
    	if(this.vertex == null)
           return this;
            
       	Iterator<Vector3> itr = this.vertex.iterator();
       	while (itr.hasNext()) {
    	Vector3 obj = itr.next();
    	
    	    		if (vertex.xi.equals(obj.xi)) {
    	      			itr.remove();
    		}
		}
        return this;
    }
    
        	    	    	    	
    public Polygon removeVertexByYi(Vector3 vertex)  {
    	if(this.vertex == null)
           return this;
            
       	Iterator<Vector3> itr = this.vertex.iterator();
       	while (itr.hasNext()) {
    	Vector3 obj = itr.next();
    	
    	    		if (vertex.yi.equals(obj.yi)) {
    	      			itr.remove();
    		}
		}
        return this;
    }
    
        	    	    	    	
    public Polygon removeVertexByZi(Vector3 vertex)  {
    	if(this.vertex == null)
           return this;
            
       	Iterator<Vector3> itr = this.vertex.iterator();
       	while (itr.hasNext()) {
    	Vector3 obj = itr.next();
    	
    	    		if (vertex.zi.equals(obj.zi)) {
    	      			itr.remove();
    		}
		}
        return this;
    }
    
            	
    
    
    
	
  
    // java serialization

    public void readExternal(ObjectInput in) throws IOException
    {
        GraphIOUtil.mergeDelimitedFrom(in, this, this);
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        GraphIOUtil.writeDelimitedTo(out, this, this);
    }

    // message method

    public Schema<Polygon> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Polygon newMessage()
    {
        return new Polygon();
    }

    public Class<Polygon> typeClass()
    {
        return Polygon.class;
    }

    public String messageName()
    {
        return Polygon.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Polygon.class.getName();
    }

    public boolean isInitialized(Polygon message)
    {
        return true;
    }

    public void mergeFrom(Input input, Polygon message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                            	case 1:
            	            		if(message.vertex == null)
                        message.vertex = new ArrayList<Vector3>();
                                        message.vertex.add(input.mergeObject(null, Vector3.getSchema()));
                                        break;
                            	            
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Polygon message) throws IOException
    {
    	    	
    	    	
    	    	if(message.vertex != null)
        {
            for(Vector3 vertex : message.vertex)
            {
                if(vertex != null) {
                   	    				output.writeObject(1, vertex, Vector3.getSchema(), true);
    				    			}
            }
        }
    	            	
    }

    public String getFieldName(int number)
    {
        switch(number)
        {
        	        	case 1: return "vertex";
        	            default: return null;
        }
    }

    public int getFieldNumber(String name)
    {
        final Integer number = __fieldMap.get(name);
        return number == null ? 0 : number.intValue();
    }

    private static final java.util.HashMap<String,Integer> __fieldMap = new java.util.HashMap<String,Integer>();
    static
    {
    	    	__fieldMap.put("vertex", 1);
    	    }
   
   public static List<String> getFields() {
	ArrayList<String> fieldNames = new ArrayList<String>();
	String fieldName = null;
	Integer i = 1;
	
    while(true) { 
		fieldName = Polygon.getSchema().getFieldName(i);
		if (fieldName == null) {
			break;
		}
		fieldNames.add(fieldName);
		i++;
	}
	return fieldNames;
}

public static Polygon parseFrom(byte[] bytes) {
	Polygon message = new Polygon();
	ProtobufIOUtil.mergeFrom(bytes, message, Polygon.getSchema());
	return message;
}

public static Polygon parseFromJson(String json) throws IOException {
	byte[] bytes = json.getBytes(Charset.forName("UTF-8"));
	Polygon message = new Polygon();
	JsonIOUtil.mergeFrom(bytes, message, Polygon.getSchema(), false);
	return message;
}

public Polygon clone() {
	byte[] bytes = this.toByteArray();
	Polygon polygon = Polygon.parseFrom(bytes);
	return polygon;
}
	
public byte[] toByteArray() {
	return toProtobuf();
	//return toJson();
}

public String toJson() {
	boolean numeric = false;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	try {
		JsonIOUtil.writeTo(out, this, Polygon.getSchema(), numeric);
	} catch (IOException e) {
		e.printStackTrace();
		throw new RuntimeException("Json encoding failed");
	}
	String json = new String(out.toByteArray(), Charset.forName("UTF-8"));
	return json;
}

public byte[] toPrefixedByteArray() {
	LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
  Schema<Polygon> schema = Polygon.getSchema();
    
	final ByteArrayOutputStream out = new ByteArrayOutputStream();
    final ProtobufOutput output = new ProtobufOutput(buffer);
    try
    {
    	schema.writeTo(output, this);
        final int size = output.getSize();
        ProtobufOutput.writeRawVarInt32Bytes(out, size);
        final int msgSize = LinkedBuffer.writeTo(out, buffer);
        assert size == msgSize;
        
        buffer.clear();
        return out.toByteArray();
    }
    catch (IOException e)
    {
        throw new RuntimeException("Serializing to a byte array threw an IOException " + 
                "(should never happen).", e);
    }
 
}

public byte[] toProtobuf() {
	LinkedBuffer buffer = LocalLinkedBuffer.get();
	byte[] bytes = null;

	try {
		bytes = ProtobufIOUtil.toByteArray(this, Polygon.getSchema(), buffer);
		buffer.clear();
	} catch (Exception e) {
		buffer.clear();
		e.printStackTrace();
		throw new RuntimeException("Protobuf encoding failed");
	}
	return bytes;
}

}