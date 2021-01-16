/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.observertc.webrtc.schemas.reports;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

/** Reports generated by the observer */
@org.apache.avro.specific.AvroGenerated
public class ObserverEventReport extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 984330288455170736L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ObserverEventReport\",\"namespace\":\"org.observertc.webrtc.schemas.reports\",\"doc\":\"Reports generated by the observer\",\"fields\":[{\"name\":\"mediaUnitId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"callName\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"userId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"browserId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"peerConnectionUUID\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"eventType\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"message\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<ObserverEventReport> ENCODER =
      new BinaryMessageEncoder<ObserverEventReport>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<ObserverEventReport> DECODER =
      new BinaryMessageDecoder<ObserverEventReport>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<ObserverEventReport> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<ObserverEventReport> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<ObserverEventReport> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<ObserverEventReport>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this ObserverEventReport to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a ObserverEventReport from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a ObserverEventReport instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static ObserverEventReport fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private String mediaUnitId;
   private String callName;
   private String userId;
   private String browserId;
   private String peerConnectionUUID;
   private String eventType;
   private String message;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public ObserverEventReport() {}

  /**
   * All-args constructor.
   * @param mediaUnitId The new value for mediaUnitId
   * @param callName The new value for callName
   * @param userId The new value for userId
   * @param browserId The new value for browserId
   * @param peerConnectionUUID The new value for peerConnectionUUID
   * @param eventType The new value for eventType
   * @param message The new value for message
   */
  public ObserverEventReport(String mediaUnitId, String callName, String userId, String browserId, String peerConnectionUUID, String eventType, String message) {
    this.mediaUnitId = mediaUnitId;
    this.callName = callName;
    this.userId = userId;
    this.browserId = browserId;
    this.peerConnectionUUID = peerConnectionUUID;
    this.eventType = eventType;
    this.message = message;
  }

  public SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public Object get(int field$) {
    switch (field$) {
    case 0: return mediaUnitId;
    case 1: return callName;
    case 2: return userId;
    case 3: return browserId;
    case 4: return peerConnectionUUID;
    case 5: return eventType;
    case 6: return message;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, Object value$) {
    switch (field$) {
    case 0: mediaUnitId = value$ != null ? value$.toString() : null; break;
    case 1: callName = value$ != null ? value$.toString() : null; break;
    case 2: userId = value$ != null ? value$.toString() : null; break;
    case 3: browserId = value$ != null ? value$.toString() : null; break;
    case 4: peerConnectionUUID = value$ != null ? value$.toString() : null; break;
    case 5: eventType = value$ != null ? value$.toString() : null; break;
    case 6: message = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'mediaUnitId' field.
   * @return The value of the 'mediaUnitId' field.
   */
  public String getMediaUnitId() {
    return mediaUnitId;
  }



  /**
   * Gets the value of the 'callName' field.
   * @return The value of the 'callName' field.
   */
  public String getCallName() {
    return callName;
  }



  /**
   * Gets the value of the 'userId' field.
   * @return The value of the 'userId' field.
   */
  public String getUserId() {
    return userId;
  }



  /**
   * Gets the value of the 'browserId' field.
   * @return The value of the 'browserId' field.
   */
  public String getBrowserId() {
    return browserId;
  }



  /**
   * Gets the value of the 'peerConnectionUUID' field.
   * @return The value of the 'peerConnectionUUID' field.
   */
  public String getPeerConnectionUUID() {
    return peerConnectionUUID;
  }



  /**
   * Gets the value of the 'eventType' field.
   * @return The value of the 'eventType' field.
   */
  public String getEventType() {
    return eventType;
  }



  /**
   * Gets the value of the 'message' field.
   * @return The value of the 'message' field.
   */
  public String getMessage() {
    return message;
  }



  /**
   * Creates a new ObserverEventReport RecordBuilder.
   * @return A new ObserverEventReport RecordBuilder
   */
  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Creates a new ObserverEventReport RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new ObserverEventReport RecordBuilder
   */
  public static Builder newBuilder(Builder other) {
    if (other == null) {
      return new Builder();
    } else {
      return new Builder(other);
    }
  }

  /**
   * Creates a new ObserverEventReport RecordBuilder by copying an existing ObserverEventReport instance.
   * @param other The existing instance to copy.
   * @return A new ObserverEventReport RecordBuilder
   */
  public static Builder newBuilder(ObserverEventReport other) {
    if (other == null) {
      return new Builder();
    } else {
      return new Builder(other);
    }
  }

  /**
   * RecordBuilder for ObserverEventReport instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ObserverEventReport>
    implements org.apache.avro.data.RecordBuilder<ObserverEventReport> {

    private String mediaUnitId;
    private String callName;
    private String userId;
    private String browserId;
    private String peerConnectionUUID;
    private String eventType;
    private String message;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.mediaUnitId)) {
        this.mediaUnitId = data().deepCopy(fields()[0].schema(), other.mediaUnitId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.callName)) {
        this.callName = data().deepCopy(fields()[1].schema(), other.callName);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.userId)) {
        this.userId = data().deepCopy(fields()[2].schema(), other.userId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.browserId)) {
        this.browserId = data().deepCopy(fields()[3].schema(), other.browserId);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.peerConnectionUUID)) {
        this.peerConnectionUUID = data().deepCopy(fields()[4].schema(), other.peerConnectionUUID);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.eventType)) {
        this.eventType = data().deepCopy(fields()[5].schema(), other.eventType);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.message)) {
        this.message = data().deepCopy(fields()[6].schema(), other.message);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
    }

    /**
     * Creates a Builder by copying an existing ObserverEventReport instance
     * @param other The existing instance to copy.
     */
    private Builder(ObserverEventReport other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.mediaUnitId)) {
        this.mediaUnitId = data().deepCopy(fields()[0].schema(), other.mediaUnitId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.callName)) {
        this.callName = data().deepCopy(fields()[1].schema(), other.callName);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.userId)) {
        this.userId = data().deepCopy(fields()[2].schema(), other.userId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.browserId)) {
        this.browserId = data().deepCopy(fields()[3].schema(), other.browserId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.peerConnectionUUID)) {
        this.peerConnectionUUID = data().deepCopy(fields()[4].schema(), other.peerConnectionUUID);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.eventType)) {
        this.eventType = data().deepCopy(fields()[5].schema(), other.eventType);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.message)) {
        this.message = data().deepCopy(fields()[6].schema(), other.message);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'mediaUnitId' field.
      * @return The value.
      */
    public String getMediaUnitId() {
      return mediaUnitId;
    }


    /**
      * Sets the value of the 'mediaUnitId' field.
      * @param value The value of 'mediaUnitId'.
      * @return This builder.
      */
    public Builder setMediaUnitId(String value) {
      validate(fields()[0], value);
      this.mediaUnitId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'mediaUnitId' field has been set.
      * @return True if the 'mediaUnitId' field has been set, false otherwise.
      */
    public boolean hasMediaUnitId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'mediaUnitId' field.
      * @return This builder.
      */
    public Builder clearMediaUnitId() {
      mediaUnitId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'callName' field.
      * @return The value.
      */
    public String getCallName() {
      return callName;
    }


    /**
      * Sets the value of the 'callName' field.
      * @param value The value of 'callName'.
      * @return This builder.
      */
    public Builder setCallName(String value) {
      validate(fields()[1], value);
      this.callName = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'callName' field has been set.
      * @return True if the 'callName' field has been set, false otherwise.
      */
    public boolean hasCallName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'callName' field.
      * @return This builder.
      */
    public Builder clearCallName() {
      callName = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'userId' field.
      * @return The value.
      */
    public String getUserId() {
      return userId;
    }


    /**
      * Sets the value of the 'userId' field.
      * @param value The value of 'userId'.
      * @return This builder.
      */
    public Builder setUserId(String value) {
      validate(fields()[2], value);
      this.userId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'userId' field has been set.
      * @return True if the 'userId' field has been set, false otherwise.
      */
    public boolean hasUserId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'userId' field.
      * @return This builder.
      */
    public Builder clearUserId() {
      userId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'browserId' field.
      * @return The value.
      */
    public String getBrowserId() {
      return browserId;
    }


    /**
      * Sets the value of the 'browserId' field.
      * @param value The value of 'browserId'.
      * @return This builder.
      */
    public Builder setBrowserId(String value) {
      validate(fields()[3], value);
      this.browserId = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'browserId' field has been set.
      * @return True if the 'browserId' field has been set, false otherwise.
      */
    public boolean hasBrowserId() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'browserId' field.
      * @return This builder.
      */
    public Builder clearBrowserId() {
      browserId = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'peerConnectionUUID' field.
      * @return The value.
      */
    public String getPeerConnectionUUID() {
      return peerConnectionUUID;
    }


    /**
      * Sets the value of the 'peerConnectionUUID' field.
      * @param value The value of 'peerConnectionUUID'.
      * @return This builder.
      */
    public Builder setPeerConnectionUUID(String value) {
      validate(fields()[4], value);
      this.peerConnectionUUID = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'peerConnectionUUID' field has been set.
      * @return True if the 'peerConnectionUUID' field has been set, false otherwise.
      */
    public boolean hasPeerConnectionUUID() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'peerConnectionUUID' field.
      * @return This builder.
      */
    public Builder clearPeerConnectionUUID() {
      peerConnectionUUID = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'eventType' field.
      * @return The value.
      */
    public String getEventType() {
      return eventType;
    }


    /**
      * Sets the value of the 'eventType' field.
      * @param value The value of 'eventType'.
      * @return This builder.
      */
    public Builder setEventType(String value) {
      validate(fields()[5], value);
      this.eventType = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'eventType' field has been set.
      * @return True if the 'eventType' field has been set, false otherwise.
      */
    public boolean hasEventType() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'eventType' field.
      * @return This builder.
      */
    public Builder clearEventType() {
      eventType = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'message' field.
      * @return The value.
      */
    public String getMessage() {
      return message;
    }


    /**
      * Sets the value of the 'message' field.
      * @param value The value of 'message'.
      * @return This builder.
      */
    public Builder setMessage(String value) {
      validate(fields()[6], value);
      this.message = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'message' field has been set.
      * @return True if the 'message' field has been set, false otherwise.
      */
    public boolean hasMessage() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'message' field.
      * @return This builder.
      */
    public Builder clearMessage() {
      message = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ObserverEventReport build() {
      try {
        ObserverEventReport record = new ObserverEventReport();
        record.mediaUnitId = fieldSetFlags()[0] ? this.mediaUnitId : (String) defaultValue(fields()[0]);
        record.callName = fieldSetFlags()[1] ? this.callName : (String) defaultValue(fields()[1]);
        record.userId = fieldSetFlags()[2] ? this.userId : (String) defaultValue(fields()[2]);
        record.browserId = fieldSetFlags()[3] ? this.browserId : (String) defaultValue(fields()[3]);
        record.peerConnectionUUID = fieldSetFlags()[4] ? this.peerConnectionUUID : (String) defaultValue(fields()[4]);
        record.eventType = fieldSetFlags()[5] ? this.eventType : (String) defaultValue(fields()[5]);
        record.message = fieldSetFlags()[6] ? this.message : (String) defaultValue(fields()[6]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<ObserverEventReport>
    WRITER$ = (org.apache.avro.io.DatumWriter<ObserverEventReport>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<ObserverEventReport>
    READER$ = (org.apache.avro.io.DatumReader<ObserverEventReport>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.mediaUnitId == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.mediaUnitId);
    }

    if (this.callName == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.callName);
    }

    if (this.userId == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.userId);
    }

    if (this.browserId == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.browserId);
    }

    if (this.peerConnectionUUID == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.peerConnectionUUID);
    }

    out.writeString(this.eventType);

    out.writeString(this.message);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.mediaUnitId = null;
      } else {
        this.mediaUnitId = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.callName = null;
      } else {
        this.callName = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.userId = null;
      } else {
        this.userId = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.browserId = null;
      } else {
        this.browserId = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.peerConnectionUUID = null;
      } else {
        this.peerConnectionUUID = in.readString();
      }

      this.eventType = in.readString();

      this.message = in.readString();

    } else {
      for (int i = 0; i < 7; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.mediaUnitId = null;
          } else {
            this.mediaUnitId = in.readString();
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.callName = null;
          } else {
            this.callName = in.readString();
          }
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.userId = null;
          } else {
            this.userId = in.readString();
          }
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.browserId = null;
          } else {
            this.browserId = in.readString();
          }
          break;

        case 4:
          if (in.readIndex() != 1) {
            in.readNull();
            this.peerConnectionUUID = null;
          } else {
            this.peerConnectionUUID = in.readString();
          }
          break;

        case 5:
          this.eventType = in.readString();
          break;

        case 6:
          this.message = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









