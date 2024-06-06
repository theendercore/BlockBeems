{
  "schemaVersion": 1,
  "id": "block_beams",
  "version": "${version}",

  "name": "Block Beams",
  "description": "A very simple mod that spawns a beam of colored particles above a block.",
  "authors": [
    "Ender"
  ],
  "contact": {
    "homepage": "https://theendercore.github.io/",
    "sources": "https://github.com/theendercore/BlockBeams"
  },

  "license": "MIT",
  "icon": "assets/block_beams/icon.png",

  "environment": "*",
  "entrypoints": {
    "client": [
      {
        "adapter": "kotlin",
        "value": "com.theendercore.block_beams.BlockBeams::onInitialize"
      }
    ]
  },
  "mixins": [
    "block_beams.mixins.json"
  ],

  "depends": {
    "fabricloader": ">=0.14.21",
    "fabric": "*",
    "minecraft": "1.20.x",
    "java": ">=17",
    "fabric-language-kotlin": ">=1.9.4+kotlin.1.8.21"
  },
  "suggests": {
    "modmenu": "*"
  }
}
