package com.broprogramming.poco.utility

/*
@Composable
fun <T> lifecycleAwareState(
    lifecycleOwner: LifecycleOwner,
    stateFlow: StateFlow<T>,
    initialState: T
): Lifecycle.State<T> {
    val lifecycleAwareStateFlow = remember(stateFlow, lifecycleOwner) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    return lifecycleAwareStateFlow.collectAsState(initialState)
}*/
