/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.lending.mortgages.interfaces.enums;

public enum MortgageTypeEnum {
    CONVENTIONAL,           // Standard mortgage
    GOVERNMENT_BACKED,      // FHA, VA, USDA (US), Help to Buy (UK), etc.
    JUMBO,                  // Above conforming loan limits
    REVERSE,                // Reverse mortgage (equity release)
    ISLAMIC_MURABAHA,       // Islamic finance - cost-plus financing
    ISLAMIC_IJARA,          // Islamic finance - lease-to-own
    ISLAMIC_MUSHARAKA,      // Islamic finance - partnership/equity participation
    ISLAMIC_DIMINISHING_MUSHARAKA, // Islamic finance - diminishing partnership
    BRIDGE,                 // Short-term bridge loan
    CONSTRUCTION,           // Construction-to-permanent
    RENOVATION,             // Purchase + renovation financing
    EQUITY_RELEASE,         // Lifetime mortgage (UK/Europe)
    SHARED_EQUITY,          // Shared ownership schemes
    SHARED_APPRECIATION,    // Lender shares in property appreciation
    BALLOON,                // Large final payment
    INTEREST_ONLY,          // Interest-only mortgage
    OFFSET,                 // Offset mortgage (savings offset interest)
    FLEXIBLE,               // Flexible payment mortgage
    TRACKER,                // Tracks base rate
    DISCOUNT,               // Discount off standard variable rate
    CAPPED,                 // Interest rate cap
    PORTABLE,               // Portable mortgage
    ASSUMABLE,              // Assumable by buyer
    NON_RECOURSE,           // Non-recourse (lender can't pursue borrower beyond collateral)
    RECOURSE,               // Full recourse to borrower
    COMMERCIAL,             // Commercial property mortgage
    BUY_TO_LET,             // Investment/rental property
    SECOND_HOME,            // Vacation/second home
    SELF_BUILD,             // Self-build mortgage
    GUARANTOR,              // Guarantor mortgage
    JOINT_BORROWER_SOLE_PROPRIETOR, // JBSP mortgage
    EQUITY_LOAN,            // Government equity loan
    RIGHT_TO_BUY,           // Right to buy scheme
    HELP_TO_BUY,            // Help to buy scheme
    LIFETIME,               // Lifetime mortgage
    HOME_REVERSION,         // Home reversion plan
    AGRICULTURAL,           // Agricultural/farm mortgage
    LAND,                   // Land-only mortgage
    OTHER                   // Other types
}

