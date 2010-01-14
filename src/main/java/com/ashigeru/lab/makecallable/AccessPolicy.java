/*
 * Copyright 2010 @ashigeru.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.ashigeru.lab.makecallable;

/**
 * Represents accessibility of the generated members.
 * @author ashigeru
 * @see MakeCallable
 */
public enum AccessPolicy {

    /**
     * Generates a member with equivalent accessibility to the original member.
     */
    DERIVED,

    /**
     * Generates a member with {@code public}.
     */
    PUBLIC,

    /**
     * Generates a member with default package access.
     */
    PACKAGE,
}
