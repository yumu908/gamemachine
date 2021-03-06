﻿using UnityEngine;
using System.Collections;
using System;
using TrackData = io.gamemachine.messages.TrackData;
using UserDefinedData = io.gamemachine.messages.UserDefinedData;

namespace GameMachine {
    namespace Common {
        public sealed class Util {

            static readonly Util _instance = new Util();
            public static Util Instance {
                get {
                    return _instance;
                }
            }

            private Settings settings;

            Util() {
                settings = Settings.Instance();
            }

            public float IntToFloat(int i, bool offset) {
                if (offset) {
                    return (i / 100f) - settings.worldOffset;
                } else {
                    return i / 100f;
                }
            }

            public int FloatToInt(float i,bool offset) {
                if (offset) {
                    return (int)(Math.Round((i + settings.worldOffset) * 100, 2));
                } else {
                    return (int)(Math.Round(i * 100, 2));
                }
            }

            public UserDefinedData Vector3ToUserDefinedData(Vector3 vec, UserDefinedData userdef) {
                userdef.x = FloatToInt(vec.x, true);
                userdef.y = FloatToInt(vec.y, true);
                userdef.z = FloatToInt(vec.z, true);
                return userdef;
            }

            public UserDefinedData QuaternionToUserDefinedData(Quaternion q, UserDefinedData userdef) {
                userdef.rx = FloatToInt(q.x, true);
                userdef.ry = FloatToInt(q.y, true);
                userdef.rz = FloatToInt(q.z, true);
                userdef.rw = FloatToInt(q.w, true);
                return userdef;
            }

            public TrackData Vector3ToTrackData(Vector3 vec, TrackData trackData) {
                trackData.x = FloatToInt(vec.x, true);
                trackData.y = FloatToInt(vec.z, true);
                trackData.z = FloatToInt(vec.y, true);
                return trackData;
            }

            public TrackData QuaternionToTrackData(Quaternion q, TrackData trackData) {
                trackData.rx = FloatToInt(q.x, true);
                trackData.ry = FloatToInt(q.y, true);
                trackData.rz = FloatToInt(q.z, true);
                trackData.rw = FloatToInt(q.w, true);
                return trackData;
            }

            public Quaternion TrackdataToQuaternion(TrackData trackData) {

                float x = (trackData.rx / 100f) - settings.worldOffset;
                float y = (trackData.ry / 100f) - settings.worldOffset;
                float z = (trackData.rz / 100f) - settings.worldOffset;
                float w = (trackData.rw / 100f) - settings.worldOffset;

                return new Quaternion(x, y, z,w);
            }

            public Vector3 TrackdataToVector3(TrackData trackData) {

                float x = (trackData.x / 100f) - settings.worldOffset;
                float y = (trackData.z / 100f) - settings.worldOffset;
                float z = (trackData.y / 100f) - settings.worldOffset;

                return new Vector3(x, y, z);
            }

            public Vector3 TrackdataToVector3(TrackData trackData, Vector3 current) {
                float x = current.x + (float)(trackData.ix / 100f);
                float z = current.z + (float)(trackData.iy / 100f);
                float y = current.y + (float)(trackData.iz / 100f);

                return new Vector3(x, y, z);
            }

            public Vector3 UserDefinedDataToVector3(UserDefinedData userdef) {

                float x = (userdef.x / 100f) - settings.worldOffset;
                float z = (userdef.y / 100f) - settings.worldOffset;
                float y = (userdef.z / 100f) - settings.worldOffset;

                return new Vector3(x, y, z);
            }

            public Quaternion UserDefinedDataToQuaternion(UserDefinedData userdef) {

                float x = (userdef.rx / 100f) - settings.worldOffset;
                float y = (userdef.ry / 100f) - settings.worldOffset;
                float z = (userdef.rz / 100f) - settings.worldOffset;
                float w = (userdef.rw / 100f) - settings.worldOffset;
                return new Quaternion(x, y, z, w);
            }
        }
    }
}
