package com.DevAsh.RecWalletBackend.Api.Request

import com.DevAsh.RecWalletBackend.Database.Transactions.Types.ExternalSource

class AddMoneyDetails(var amount:Number, var externalSource: ExternalSource)