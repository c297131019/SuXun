// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PushMsgNotify.proto

package com.yuntongxun.mcm.core.protobuf;

public final class PushMsgNotify {
  private PushMsgNotify() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface PushMsgNotifyInnerOrBuilder extends
      // @@protoc_insertion_point(interface_extends:PushMsgNotifyInner)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>optional uint64 version = 1;</code>
     *
     * <pre>
     * 版本号 
     * </pre>
     */
    boolean hasVersion();
    /**
     * <code>optional uint64 version = 1;</code>
     *
     * <pre>
     * 版本号 
     * </pre>
     */
    long getVersion();
  }
  /**
   * Protobuf type {@code PushMsgNotifyInner}
   */
  public static final class PushMsgNotifyInner extends
      com.google.protobuf.GeneratedMessageLite implements
      // @@protoc_insertion_point(message_implements:PushMsgNotifyInner)
      PushMsgNotifyInnerOrBuilder {
    // Use PushMsgNotifyInner.newBuilder() to construct.
    private PushMsgNotifyInner(com.google.protobuf.GeneratedMessageLite.Builder builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PushMsgNotifyInner(boolean noInit) { this.unknownFields = com.google.protobuf.ByteString.EMPTY;}

    private static final PushMsgNotifyInner defaultInstance;
    public static PushMsgNotifyInner getDefaultInstance() {
      return defaultInstance;
    }

    public PushMsgNotifyInner getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.ByteString unknownFields;
    private PushMsgNotifyInner(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.ByteString.Output unknownFieldsOutput =
          com.google.protobuf.ByteString.newOutput();
      com.google.protobuf.CodedOutputStream unknownFieldsCodedOutput =
          com.google.protobuf.CodedOutputStream.newInstance(
              unknownFieldsOutput);
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFieldsCodedOutput,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              version_ = input.readUInt64();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        try {
          unknownFieldsCodedOutput.flush();
        } catch (java.io.IOException e) {
        // Should not happen
        } finally {
          unknownFields = unknownFieldsOutput.toByteString();
        }
        makeExtensionsImmutable();
      }
    }
    public static com.google.protobuf.Parser<PushMsgNotifyInner> PARSER =
        new com.google.protobuf.AbstractParser<PushMsgNotifyInner>() {
      public PushMsgNotifyInner parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PushMsgNotifyInner(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PushMsgNotifyInner> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int VERSION_FIELD_NUMBER = 1;
    private long version_;
    /**
     * <code>optional uint64 version = 1;</code>
     *
     * <pre>
     * 版本号 
     * </pre>
     */
    public boolean hasVersion() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional uint64 version = 1;</code>
     *
     * <pre>
     * 版本号 
     * </pre>
     */
    public long getVersion() {
      return version_;
    }

    private void initFields() {
      version_ = 0L;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeUInt64(1, version_);
      }
      output.writeRawBytes(unknownFields);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(1, version_);
      }
      size += unknownFields.size();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    /**
     * Protobuf type {@code PushMsgNotifyInner}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner, Builder>
        implements
        // @@protoc_insertion_point(builder_implements:PushMsgNotifyInner)
        com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInnerOrBuilder {
      // Construct using com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        version_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner getDefaultInstanceForType() {
        return com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner.getDefaultInstance();
      }

      public com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner build() {
        com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner buildPartial() {
        com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner result = new com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.version_ = version_;
        result.bitField0_ = to_bitField0_;
        return result;
      }

      public Builder mergeFrom(com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner other) {
        if (other == com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner.getDefaultInstance()) return this;
        if (other.hasVersion()) {
          setVersion(other.getVersion());
        }
        setUnknownFields(
            getUnknownFields().concat(other.unknownFields));
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.yuntongxun.mcm.core.protobuf.PushMsgNotify.PushMsgNotifyInner) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long version_ ;
      /**
       * <code>optional uint64 version = 1;</code>
       *
       * <pre>
       * 版本号 
       * </pre>
       */
      public boolean hasVersion() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional uint64 version = 1;</code>
       *
       * <pre>
       * 版本号 
       * </pre>
       */
      public long getVersion() {
        return version_;
      }
      /**
       * <code>optional uint64 version = 1;</code>
       *
       * <pre>
       * 版本号 
       * </pre>
       */
      public Builder setVersion(long value) {
        bitField0_ |= 0x00000001;
        version_ = value;
        
        return this;
      }
      /**
       * <code>optional uint64 version = 1;</code>
       *
       * <pre>
       * 版本号 
       * </pre>
       */
      public Builder clearVersion() {
        bitField0_ = (bitField0_ & ~0x00000001);
        version_ = 0L;
        
        return this;
      }

      // @@protoc_insertion_point(builder_scope:PushMsgNotifyInner)
    }

    static {
      defaultInstance = new PushMsgNotifyInner(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:PushMsgNotifyInner)
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}
