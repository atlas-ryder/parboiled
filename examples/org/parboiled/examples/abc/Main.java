/*
 * Copyright (C) 2009 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.examples.abc;

import org.parboiled.Parboiled;
import org.parboiled.common.StringUtils;
import static org.parboiled.support.ParseTreeUtils.printNodeTree;
import org.parboiled.support.ParsingResult;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.print("\nEnter an expression (single RETURN to exit): ");
            String input = new Scanner(System.in).nextLine();
            if (StringUtils.isEmpty(input)) break;

            AbcParser parser = Parboiled.createParser(AbcParser.class, null);
            ParsingResult<?> result = parser.parse(parser.S(), input);

            System.out.println(input + " = " + result.parseTreeRoot.getValue() + '\n');
            System.out.println(printNodeTree(result) + '\n');

            if (result.hasErrors()) {
                System.out.println(StringUtils.join(result.parseErrors, "---\n"));
            }
        }
    }

}