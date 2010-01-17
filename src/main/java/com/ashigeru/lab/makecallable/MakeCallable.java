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

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides {@link java.util.concurrent.Callable} interfaces for the annotated methods.
 * <p>
 * Each annotated method:
 * </p>
 * <ul>
 * <li> must be declared as {@code public}, {@code protected}, or default package access </li>
 * <li> only can throw exceptions either subtype of {@link Exception} or {@link Error} except type variables </li>
 * </ul>
 * <pre>
 * // the original source
 * package com.example;
 * public class Hoge {
 *   &#64;MakeCallable public String foo(int bar) throws FugaException {
 *     return ...;
 *   }
 * }
 * </pre>
 * <pre>
 * // the generated source
 * package com.example;
 * public class HogeDefer {
 *   private Hoge __delegate__;
 *   public HogeDefer(Hoge delegate) {
 *     this.__delegate__ = delegate;
 *   }
 *   public foo foo(int bar) {
 *     return new foo(this.__delegate__, bar);
 *   }
 *   public class foo implements Callable&lt;String&gt;, Serializable {
 *     private HodeDefer __delegate__;
 *     private int bar;
 *     foo(HogeDefer __delegate__, int bar) {
 *       this.__delegate__ = __delegate__;
 *       this.bar = bar;
 *     }
 *     public String call() throws FugaException {
 *       return __delegate__.foo(bar);
 *     }
 *   }
 * }
 * </pre>
 * @author ashigeru
 * @see MakeCallable.Container
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
public @interface MakeCallable {

    /**
     * The simple name pattern of the generated callable class for the method.
     * <p>
     * This pattern must be the same manner with {@link java.text.MessageFormat#format(String, Object...)},
     * and <code>"{0}"</code> in the pattern will be replaced with the simple name of the original method.
     * </p>
     */
    String name() default "{0}";

    /**
     * Accessibility of the generated method.
     */
    AccessPolicy accessible() default AccessPolicy.DERIVED;

    /**
     * If {@code true}, the generated class implements {@link Serializable}.
     */
    boolean serializable() default true;

    /**
     * An annotation for configuring the container class for each generated callable classes.
     * @author ashigeru
     * @see MakeCallable
     */
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Container {

        /**
         * The simple name pattern of the generated container class.
         * <p>
         * This pattern must be the same manner with {@link java.text.MessageFormat#format(String, Object...)},
         * and <code>"{0}"</code> in the pattern will be replaced with the simple name of the original class.
         * </p>
         */
        String name() default "{0}Defer";

        /**
         * Accessibility of the generated container class.
         */
        AccessPolicy accessible() default AccessPolicy.DERIVED;
    }
}
