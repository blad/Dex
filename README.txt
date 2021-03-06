Dex
---------------------------------------
Dex is a utility for Git that makes annotating commits simple. Dex associates 
a branch to a ticket number or arbitrary value. Every commit on that branch is 
annotated with that ticket number or value.

Example annotation with the value: `ENG-042`:

   [ENG-042] Fix the Universe


Installation
---------------------------------------
1. Download the latest release from: https://github.com/blad/Dex/releases
2. Extract the contents of the release bundle and place contents into your PATH.
3. Run: `dex-tracker install`. This installs a global git alias for `track`.
4. Ready to use with any repository.


Usage
---------------------------------------
Associating a ticket number or value to the current branch:
  | git track {ticket_number}

Associating a ticket number or value to an existing branch:
  | git checkout {branch_name} 
  | git track {ticket_number}

Associating a ticket number or value to a new branch:
  | git checkout -b {branch_name} 
  | git track {ticket_number}


License
----------------------------------------
Copyright 2020 Bladymir Tellez <btellez@gmail.com>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
